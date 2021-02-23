package com.aligmohammad.doctorappclient.ui.dialogs.appointmentmanagment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.databinding.AppointmentManagmentBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import navigateSafe


class AppointmentManagementBottomSheetFragment : BottomSheetDialogFragment(), OnDialogInteract {

    private lateinit var viewModel: AppointmentManagmentBottomSheetViewModel
    private lateinit var binding: AppointmentManagmentBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.appointment_managment_bottom_sheet_fragment,
            container,
            false
        )
        viewModel =
            ViewModelProvider(this).get(AppointmentManagmentBottomSheetViewModel::class.java)
        binding.viewModel = viewModel
        binding.listener = this

        binding.addAppointment.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.appointmentManagementBottomSheetFragment) {
                navigateSafe(AppointmentManagementBottomSheetFragmentDirections.managerToNewAppointment())
            }
        }

        binding.currentAppointment.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.appointmentManagementBottomSheetFragment) {
                navigateSafe(AppointmentManagementBottomSheetFragmentDirections.managerToList())
            }
        }

        binding.cancelAppointment.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.appointmentManagementBottomSheetFragment) {
                navigateSafe(AppointmentManagementBottomSheetFragmentDirections.managerToCancelAppointment())
            }
        }

        binding.appointmentDuration.setOnClickListener {
            showDialog()
//            val cal = java.util.Calendar.getInstance()
//            val timeSetListener =
//                TimePickerDialog.OnTimeSetListener { timePickerDialog: TimePickerDialog, i: Int, i1: Int, i2: Int -> android.app.TimePickerDialog.OnTimeSetListener { p0, p1, p2 -> } }
//            TimePickerDialog.newInstance(
//                timeSetListener,
//                cal.get(Calendar.HOUR_OF_DAY),
//                cal.get(Calendar.MINUTE),
//                true
//            ).show(fragmentManager!!, "Time Dialog")
        }

        return binding.root
    }

    fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose Duration")

// add a list
        val appointmentsDurations = arrayOf("5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55")
        builder.setItems(appointmentsDurations) { dialog, which ->
            val userId = UserSingleton.getCurrentUser().uuid
            if (userId != null) {
                val db = Firebase.database.reference
                db.child("Doctors").child(userId).child("appointmentDuration")
                    .setValue(appointmentsDurations[which]).addOnCompleteListener {
                        dialog.dismiss()
                    }.addOnFailureListener {
                        dialog.dismiss()
                    }
            }
        }

// create and show the alert dialog
        val dialog = builder.create()
        dialog.show()

    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }

}