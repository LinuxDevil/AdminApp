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

package com.aligmohammad.doctorappclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.Doctor
import com.aligmohammad.doctorappclient.data.model.firebasemodels.DeviceFirebaseModel
import com.aligmohammad.doctorappclient.databinding.DoctorListItemBinding
import com.aligmohammad.doctorappclient.ui.fragments.doctorlist.DoctorListFragmentDirections

class DoctorListRecyclerViewAdapter(
    private val doctors: List<DeviceFirebaseModel>,
    private val type: String = "doctor"
) : RecyclerView.Adapter<DoctorListRecyclerViewAdapter.DoctorListViewHolder>(), OnMenuItemClick {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorListViewHolder {

        val layout = if (type == "doctor") R.layout.doctor_list_item else R.layout.doctor_list_item

        val binding: DoctorListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        binding.listener = this
        return DoctorListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorListViewHolder, position: Int) {
        holder.doctorItemBinding.data = doctors[position]
    }

    override fun getItemCount(): Int = doctors.size

    inner class DoctorListViewHolder(val doctorItemBinding: DoctorListItemBinding) :
        RecyclerView.ViewHolder(doctorItemBinding.root)

    override fun onClick(v: View) {
        val doctor = doctors.filter {
            it.name == v.tag.toString()
        }[0]

        Navigation.findNavController(v)
            .navigate(DoctorListFragmentDirections.doctorListToDoctorProfile(doctor))
    }

}