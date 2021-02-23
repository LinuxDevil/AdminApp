package com.aligmohammad.doctorappclient.ui.fragments.appointment_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.firebasemodels.AppointmentFirebaseModel
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.databinding.AppointmentListFragmentBinding
import com.aligmohammad.doctorappclient.ui.adapters.AppointmentRecyclerViewAdapter
import com.aligmohammad.doctorappclient.ui.adapters.InProgressAppointmentRecyclerViewAdapter
import com.aligmohammad.doctorappclient.util.snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppointmentListFragment : Fragment() {

    private lateinit var viewModel: AppointmentListViewModel
    private lateinit var binding: AppointmentListFragmentBinding

    private var inProgressAppointments = arrayListOf<AppointmentFirebaseModel>()
    private var appointmentHistory = arrayListOf<AppointmentFirebaseModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.appointment_list_fragment, container, false)
        viewModel = ViewModelProvider(this).get(AppointmentListViewModel::class.java)

        getAllAppointments()
        initRecycler()

        binding.historyButton.setOnClickListener {
            binding.appointmentList.apply {
                adapter = AppointmentRecyclerViewAdapter(appointmentHistory, 0)
            }
        }

        binding.inProgressButton.setOnClickListener {
            binding.appointmentList.apply {
                adapter = InProgressAppointmentRecyclerViewAdapter(inProgressAppointments, 1)
            }
        }

        return binding.root
    }

    private fun initRecycler() {
        binding.appointmentList.apply {
            adapter = AppointmentRecyclerViewAdapter(appointmentHistory)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getAllAppointments() {
        val userId = UserSingleton.getCurrentUser().uuid
        val db = Firebase.database.reference
        db.child("Doctors").child(userId!!).child("Appointments")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    inProgressAppointments.clear()
                    appointmentHistory.clear()
                    snapshot.children.forEach { snap ->
                        db.child("Appointments").child(snap.value.toString()).addListenerForSingleValueEvent(object:
                            ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val appointment: AppointmentFirebaseModel =
                                    snapshot.getValue(AppointmentFirebaseModel::class.java)!!
                                if (appointment.inProgress) {
                                    inProgressAppointments.add(appointment)
                                } else {
                                    appointmentHistory.add(appointment)
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                            }

                        })

                        initRecycler()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    binding.appointmentList.snackbar(error.message)
                }
            })
    }


}