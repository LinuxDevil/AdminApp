package com.aligmohammad.doctorappclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.Appointment
import com.aligmohammad.doctorappclient.data.model.firebasemodels.AppointmentFirebaseModel
import com.aligmohammad.doctorappclient.databinding.AppointmentItemBinding
import com.aligmohammad.doctorappclient.ui.fragments.appointment_list.AppointmentListFragmentDirections

class AppointmentRecyclerViewAdapter(
    private val appointments: List<AppointmentFirebaseModel>,
    val type: Int = 0
) : RecyclerView.Adapter<AppointmentRecyclerViewAdapter.AppointmentViewHolder>(), OnMenuItemClick {

    var clickIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {

        val binding: AppointmentItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.appointment_item,
            parent,
            false
        )
        binding.listener = this
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.appointmentItemBinding.data = appointments[position]
        clickIndex = position
    }

    override fun getItemCount(): Int = appointments.size

    inner class AppointmentViewHolder(val appointmentItemBinding: AppointmentItemBinding) :
        RecyclerView.ViewHolder(appointmentItemBinding.root)

    override fun onClick(v: View) {
        val appointment = appointments.filter {
            it.userId == v.tag.toString()
        }[0]

//        Navigation.findNavController(v)
//            .navigate(AppointmentListFragmentDirections.listToDetails(appointment))
    }
}