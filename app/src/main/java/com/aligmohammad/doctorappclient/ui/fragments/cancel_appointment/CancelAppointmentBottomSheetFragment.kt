package com.aligmohammad.doctorappclient.ui.fragments.cancel_appointment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.firebasemodels.AppointmentFirebaseModel
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.databinding.CancelAppointmentBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.aligmohammad.doctorappclient.util.snackbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.appointment_managment_bottom_sheet_fragment.*
import kotlinx.android.synthetic.main.cancel_appointment_bottom_sheet_fragment.view.*
import kotlinx.android.synthetic.main.cancel_appointment_bottom_sheet_fragment.view.timeEditText
import java.util.*

class CancelAppointmentBottomSheetFragment : BottomSheetDialogFragment(), TimePickerDialog.OnTimeSetListener, OnDialogInteract,DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: CancelAppointmentBottomSheetViewModel
    private lateinit var binding: CancelAppointmentBottomSheetFragmentBinding
    private var shiftSelected: String = ""

    private var timeSelected: String = ""
    private var toTimeSelected: String = ""

    private var dateSelected: String = ""
    private var toDateSelected: String = ""

    private var firstTime = false
    private var firstDate = false

    private var appointmentString = arrayListOf<String>()

    private var inProgressAppointments = arrayListOf<AppointmentFirebaseModel>()
    private var appointmentHistory = arrayListOf<AppointmentFirebaseModel>()

    private var selectedAppointment = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cancel_appointment_bottom_sheet_fragment, container, false)
        viewModel = ViewModelProvider(this).get(CancelAppointmentBottomSheetViewModel::class.java)

        binding.listener = this

        getAllAppointments()

        binding.dateEditText.setOnFocusChangeListener { view, b ->
            firstDate = true
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

        binding.timeEditText.setOnFocusChangeListener { view, isFocused ->
            firstTime = true
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

        binding.toDateEditText.setOnFocusChangeListener { view, b ->
            firstDate = false
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

        binding.endTimeEditText.setOnFocusChangeListener { view, isFocused ->
            firstTime = false
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

        binding.dateEditText.setOnClickListener {
            firstDate = true
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show(fragmentManager!!, "DatePicker")
        }

        binding.timeEditText.setOnClickListener {
            firstTime = true
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

        binding.toDateEditText.setOnClickListener {
            firstDate = false
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show(fragmentManager!!, "DatePicker")
        }

        binding.endTimeEditText.setOnClickListener {
            firstTime = false
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

        binding.confirmButton.setOnClickListener {
            cancelAppointment(binding.daysSpinner.selectedItem.toString())
        }

        binding.daysSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (appointmentString.size > 1) {
                    selectedAppointment = appointmentString[p2]
                    Log.v("doctorapp", selectedAppointment)
                } else {
                    Log.v("doctorapp", "There's no data in appointmentString ${appointmentString.size}")

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        return binding.root
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        var hourCalculation = hourOfDay
        var timing = "AM"
        if (hourCalculation >= 13) {
            hourCalculation -= 12
            timing = "PM"
        }

        if (firstTime) {
            timeSelected = "${hourCalculation}:${minute} ${timing}"
            binding.timeEditText.setText("${hourCalculation}:${minute} ${timing}")
        } else {
            toTimeSelected = "${hourCalculation}:${minute} ${timing}"
            binding.endTimeEditText.setText("${hourCalculation}:${minute} ${timing}")
        }

    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        view!!.dismiss()
        if (firstDate) {
            dateSelected = "${year}/${monthOfYear}/$dayOfMonth"
            binding.dateEditText.setText("${year}/${monthOfYear}/$dayOfMonth")
        } else {
            toDateSelected = "${year}/${monthOfYear}/$dayOfMonth"
            binding.toDateEditText.setText("${year}/${monthOfYear}/$dayOfMonth")
        }
    }

    private fun getAllAppointments() {
        val userId = UserSingleton.getCurrentUser().uuid
        val db = Firebase.database.reference
        if (userId != null) {
            db.child("Doctors").child(userId).child("Appointments")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        inProgressAppointments.clear()
                        appointmentHistory.clear()
                        snapshot.children.forEach { snap ->
                            db.child("Appointments").child(snap.value.toString())
                                .addListenerForSingleValueEvent(object :
                                    ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        val appointment: AppointmentFirebaseModel =
                                            snapshot.getValue(AppointmentFirebaseModel::class.java)!!
                                        if (appointment.inProgress) {
                                            inProgressAppointments.add(appointment)
                                            Log.v("doctorapp", "Adding to in progress")
                                        } else {
                                            appointmentHistory.add(appointment)
                                            Log.v("doctorapp", "Adding to history")
                                        }
                                        appointmentString.add(appointment.userId!!)
                                        initRecycler()
                                        Log.v("doctorapp", "Adding to appointmentString ${appointmentString.size}")
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                    }

                                })
                            initRecycler()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        binding.toDateEditText.snackbar(error.message)
                    }
                })
        }
    }

    private fun initRecycler() {
        binding.daysSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, appointmentString)
    }

    private fun cancelAppointment(tag: String) {
        Log.v("doctorapp", "InProgress Size: ${inProgressAppointments.size}")
        val appointmentFiltered = inProgressAppointments.filter {
            it.doctorId == tag
        }
        var appointment: AppointmentFirebaseModel? = null
        if (appointmentFiltered.isNotEmpty()) {
            appointment = appointmentFiltered[0]
        }
        if (appointment != null) {
            val db = Firebase.database.reference
            db.child("Appointments").child(appointment!!.uuid!!).removeValue()
            db.child("Users").child(appointment.userId!!.substring(1, appointment.userId!!.length))
                .child("Appointments")
                .child(appointment.uuid!!).removeValue()

            dismiss()

            if (appointment.doctorId != null && appointment.doctorId!!.isNotEmpty()) {
                db.child("Doctors").child(appointment.doctorId!!).child("Appointments")
                    .child(appointment.uuid!!).removeValue()
            } else {
                db.child("Places/Labs").child(appointment.doctorId!!).child("Appointments")
                    .child(appointment.uuid!!).removeValue()
                db.child("Places/X-Rays").child(appointment.doctorId!!).child("Appointments")
                    .child(appointment.uuid!!).removeValue()
            }
        } else {
            Log.v("doctorapp", "appointment is null ${tag}")
        }
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }

}