package com.aligmohammad.doctorappclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.firebasemodels.AppointmentFirebaseModel
import com.aligmohammad.doctorappclient.databinding.InProgressAppointmentBinding

class InProgressAppointmentRecyclerViewAdapter(
    private val appointments: List<AppointmentFirebaseModel>,
    val type: Int = 0
) : RecyclerView.Adapter<InProgressAppointmentRecyclerViewAdapter.InProgressAppointmentViewHolder>(),
    OnMenuItemClick {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InProgressAppointmentViewHolder {

        val binding: InProgressAppointmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.in_progress_appointment,
            parent,
            false
        )
        binding.listener = this
        return InProgressAppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InProgressAppointmentViewHolder, position: Int) {
        holder.appointmentItemBinding.data = appointments[position]
    }

    override fun getItemCount(): Int = appointments.size

    inner class InProgressAppointmentViewHolder(val appointmentItemBinding: InProgressAppointmentBinding) :
        RecyclerView.ViewHolder(appointmentItemBinding.root)

    override fun onClick(v: View) {
        val appointment = appointments.filter {
            it.userId == v.tag.toString()
        }[0]

//        Navigation.findNavController(v)
//            .navigate(AppointmentListFragmentDirections.listToDetails(appointment))
    }
}