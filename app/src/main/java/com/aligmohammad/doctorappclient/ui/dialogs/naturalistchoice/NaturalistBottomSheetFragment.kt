/*******************************************************************************
 *
 * Copyright RectiCode(c) 2020.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and de-compilation.
 *
 * Created by Ali Mohammad
 *
 ******************************************************************************/

package com.aligmohammad.doctorappclient.ui.dialogs.naturalistchoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.Place
import com.aligmohammad.doctorappclient.data.model.firebasemodels.AppointmentFirebaseModel
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.databinding.NaturalistBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.aligmohammad.doctorappclient.ui.dialogs.datedialogfragment.DateAndTimeBottomSheetFragmentDirections
import com.aligmohammad.doctorappclient.util.hideKeyboard
import com.aligmohammad.doctorappclient.util.snackbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.labs_bottom_sheet_fragment.view.*
import kotlinx.android.synthetic.main.naturalist_bottom_sheet_fragment.view.*
import kotlinx.android.synthetic.main.naturalist_bottom_sheet_fragment.view.dateEditText
import kotlinx.android.synthetic.main.naturalist_bottom_sheet_fragment.view.timeEditText
import kotlinx.android.synthetic.main.opertaion_bottom_sheet_fragment.view.*
import navigateSafe
import java.util.*

class NaturalistBottomSheetFragment : BottomSheetDialogFragment(),
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, OnDialogInteract {

    private lateinit var viewModel: NaturalistBottomSheetViewModel
    private lateinit var binding: NaturalistBottomSheetFragmentBinding

    private var places = arrayListOf<String>()

    private var selectedTime = ""
    private var selectedDate = ""
    private var selectedPlace = ""
    private var selectedTest = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.naturalist_bottom_sheet_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(NaturalistBottomSheetViewModel::class.java)
        binding.viewModel = viewModel

        getPlaces()
        initializeUI()

        binding.listener = this
        binding.root.dateEditText.setOnFocusChangeListener { view, b ->
            if (b) {
                val calendar = Calendar.getInstance()
                val datePicker = DatePickerDialog.newInstance(
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show(fragmentManager!!, "DatePicker")
            }
        }
        binding.root.timeEditText.setOnFocusChangeListener { view, isFocused ->
            if (isFocused) {
                val calendar = Calendar.getInstance()
                val timePicker = TimePickerDialog.newInstance(
                    this,
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND),
                    false
                )
                timePicker.show(fragmentManager!!, "TimePicker")
            }
        }

        binding.root.dateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show(fragmentManager!!, "DatePicker")
        }
        binding.root.timeEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog.newInstance(
                this,
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND),
                false
            )
            timePicker.show(fragmentManager!!, "TimePicker")
        }


        return binding.root
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }

    override fun onButtonClicked(view: View) {
        addUserAppointment()
    }

    private fun initializeUI() {
        binding.placeSpinner.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, places)
    }

    private fun getPlaces() {
        val db = Firebase.database.reference
        db.child("Places/Labs").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { snap ->
                    val place: Place = snap.getValue(Place::class.java)!!
                    places.add(place.locationName!!)
                    initializeUI()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }


    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        view!!.dismiss()
        binding.root.dateEditText.setText("${year}/${monthOfYear}/$dayOfMonth")
        selectedDate = "${year}/${monthOfYear}/$dayOfMonth"
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        var hourCalculation = hourOfDay
        var timing = "AM"
        if (hourCalculation >= 13) {
            hourCalculation -= 12
            timing = "PM"
        }
        binding.root.timeEditText.setText("${hourCalculation}:${minute} ${timing}")
        selectedTime = "${hourCalculation}:${minute} ${timing}"
    }

    private fun addUserAppointment() {

        selectedPlace = binding.placeSpinner.selectedItem.toString()

        // 1- Get user id
        val userId = UserSingleton.getCurrentUser().uuid
        // 2- Create appointment

        // 3- Push Appointment to db
        val database = Firebase.database.reference
        val appointmentUuid = database.push().key

        val appointment = AppointmentFirebaseModel(
            selectedDate,
            selectedTime,
            "Morning",
            null,
            userId,
            selectedPlace,
            null,
            true,
            appointmentUuid
        )
        if (appointmentUuid != null) {
            hideKeyboard()
            database.child("Appointments").child(appointmentUuid).setValue(appointment)
                .addOnCompleteListener {
                    // 4- Add appointment id to user
                    // 5- Push user changes
                    database.child("Doctors").child(userId!!).child("Appointments")
                        .child(appointmentUuid).setValue(appointmentUuid).addOnCompleteListener {
                            database.child("Places").child("Labs").child(selectedPlace)
                                .child("Appointments").child(appointmentUuid)
                                .setValue(appointmentUuid)
                            val navController = Navigation.findNavController(activity!!, R.id.fragment)
                            if (navController.currentDestination?.id == R.id.dateAndTimeBottomSheetFragment)
                                this.navigateSafe(DateAndTimeBottomSheetFragmentDirections.backToHome())
                            else {
                                Log.v("MainActivity", navController.currentDestination?.label.toString())
                            }
                        }.addOnFailureListener {
                            binding.dateEditText.snackbar(it.localizedMessage)
                            dismiss()
                        }
                }.addOnFailureListener {
                    binding.dateEditText.snackbar(it.localizedMessage)
                    dismiss()
                }
        }

    }

}


