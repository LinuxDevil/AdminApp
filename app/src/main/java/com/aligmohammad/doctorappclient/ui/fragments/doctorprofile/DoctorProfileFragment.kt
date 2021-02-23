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

package com.aligmohammad.doctorappclient.ui.fragments.doctorprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.firebasemodels.DeviceOrder
import com.aligmohammad.doctorappclient.data.model.firebasemodels.PharmacyOrder
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.databinding.DoctorProfileFragmentBinding
import com.aligmohammad.doctorappclient.util.hideKeyboard
import com.aligmohammad.doctorappclient.util.snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.doctor_profile_fragment.view.*
import java.util.*

class DoctorProfileFragment : Fragment(),
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var viewModel: DoctorProfileViewModel
    private lateinit var binding: DoctorProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.doctor_profile_fragment, container, false)
        viewModel = ViewModelProvider(this).get(DoctorProfileViewModel::class.java)

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)

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

        arguments?.let {
            binding.doctor = DoctorProfileFragmentArgs.fromBundle(it).doctor
        }

        binding.confirmButton.setOnClickListener {
            addUserAppointment()
        }

        return binding.root
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        view!!.dismiss()
        binding.root.dateEditText.setText("${year}/${monthOfYear}/$dayOfMonth")
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        var hourCalculation = hourOfDay
        var timing = "AM"
        if (hourCalculation >= 13) {
            hourCalculation -= 12
            timing = "PM"
        }
        binding.root.timeEditText.setText("${hourCalculation}:${minute} ${timing}")
    }

    private fun addUserAppointment() {
        // 1- Get user id
        val userId = UserSingleton.getCurrentUser().uuid
        // 2- Create appointment
        val order = DeviceOrder(
            userId,
            binding.doctor!!.uuid!!,
            binding.doctor!!.name!!,
            null
        )
        // 3- Push Appointment to db
        val database = Firebase.database.reference
        val orderUuid = database.push().key
        if (orderUuid != null) {
            hideKeyboard()
            database.child("Orders").child(orderUuid).setValue(order).addOnCompleteListener {
                // 4- Add appointment id to user
                // 5- Push user changes
                database.child("Doctors").child(userId!!)
                    .child("Orders").child(orderUuid).setValue(orderUuid)
                    .addOnCompleteListener {
                        database.child("Devices").child(binding.doctor!!.uuid!!)
                            .child("Orders").child(orderUuid).setValue(orderUuid)

                        binding.dateTextView.snackbar("Reserved!")
                    }.addOnFailureListener {
                        binding.dateTextView.snackbar(it.localizedMessage)
                    }
            }.addOnFailureListener {
                binding.dateEditText.snackbar(it.localizedMessage)
            }
        }

    }



}