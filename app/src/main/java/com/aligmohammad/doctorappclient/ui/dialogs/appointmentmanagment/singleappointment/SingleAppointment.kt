package com.aligmohammad.doctorappclient.ui.dialogs.appointmentmanagment.singleappointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.firebasemodels.AppointmentFirebaseModel
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.databinding.SingleAppointmentFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.aligmohammad.doctorappclient.util.snackbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

class SingleAppointment : BottomSheetDialogFragment(), OnDialogInteract,
    TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: SingleAppointmentViewModel
    private lateinit var binding: SingleAppointmentFragmentBinding

    private var arrayOfDates = listOf<String>()
    private var arrayOfTimes = listOf<String>()

    private var shiftSelected: String = ""

    private var timeSelected: String = ""
    private var toTimeSelected: String = ""

    private var dateSelected: String = ""
    private var toDateSelected: String = ""

    private var firstTime = false
    private var firstDate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.single_appointment_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(SingleAppointmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.listener = this
        initializeRecycler()

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

        binding.dayTime.setOnClickListener {
            shiftSelected = "Morning"
            binding.nightTime.background = null
            binding.dayTime.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.active_drawable)
        }

        binding.nightTime.setOnClickListener {
            shiftSelected = "After noon"
            binding.dayTime.background = null
            binding.nightTime.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.active_drawable)
        }

        binding.reserveButton.setOnClickListener {
            addUserAppointment()
        }

        return binding.root
    }

    private fun initializeRecycler() {
//        binding.dateRecyclerView.apply {
//            adapter = DateRecyclerAdapter(viewModel.getDates())
//            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        }
//
//        binding.timeRecyclerView.apply {
//            adapter = TimeRecyclerAdapter(viewModel.getTimes())
//            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        }
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
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

    private fun addUserAppointment() {
        // 1- Get user id
        val userId = UserSingleton.getCurrentUser().uuid
        val dueTime = "$timeSelected - $toTimeSelected"
        val dueDate = "$dateSelected - $toDateSelected"
        val database = Firebase.database.reference
        val appointmentUuid = database.push().key
        // 2- Create appointment
        val appointment = AppointmentFirebaseModel(
            dueDate,
            dueTime,
            shiftSelected,
            userId,
            userId,
            null,
            null,
            true,
            appointmentUuid
        )
        // 3- Push Appointment to db
        if (appointmentUuid != null) {
            database.child("Appointments").child(appointmentUuid).setValue(appointment)
                .addOnCompleteListener {
                    // 4- Add appointment id to user
                    // 5- Push user changes
                    database.child("Doctors").child(userId!!)
                        .child("Appointments").child(appointmentUuid).setValue(appointmentUuid)
                        .addOnCompleteListener {
                            binding.reserveButton.snackbar("Reserved!")
                            dismiss()

                        }.addOnFailureListener {
                            binding.reserveButton.snackbar(it.localizedMessage!!)
                        }

                }.addOnFailureListener {
                    binding.reserveButton.snackbar(it.localizedMessage!!)
                }
        }

    }

}