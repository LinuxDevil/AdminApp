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
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.RecyclerMenuItem
import com.aligmohammad.doctorappclient.data.model.firebasemodels.DeviceTypesFirebaseModel
import com.aligmohammad.doctorappclient.databinding.DoctorMajorItemBinding


class MyDoctorMajorRecyclerViewAdapter(
    private val values: List<DeviceTypesFirebaseModel>,
    private val onMenuItemClick: OnMenuItemClick
) : RecyclerView.Adapter<MyDoctorMajorRecyclerViewAdapter.ViewHolder>() {

    private lateinit var binding: DoctorMajorItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.doctor_major_item,
            parent,
            false
        )
        binding.listener = onMenuItemClick
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        binding.menuItem = item
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: DoctorMajorItemBinding) : RecyclerView.ViewHolder(view.root)
}