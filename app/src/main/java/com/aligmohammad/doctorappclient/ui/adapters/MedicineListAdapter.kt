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
import com.aligmohammad.doctorappclient.data.model.Medicine
import com.aligmohammad.doctorappclient.databinding.MedicineItemBinding

class MedicineListAdapter(private val medicineList: List<Medicine>) :
    RecyclerView.Adapter<MedicineListAdapter.MedicineListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineListViewHolder {
        val binding: MedicineItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.medicine_item,
            parent,
            false
        )
        return MedicineListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineListViewHolder, position: Int) {
        holder.medicineItemBinding.medicine = medicineList[position]
    }

    override fun getItemCount(): Int = medicineList.size

    inner class MedicineListViewHolder(val medicineItemBinding: MedicineItemBinding) :
        RecyclerView.ViewHolder(medicineItemBinding.root)

}