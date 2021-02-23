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

/**
 * Copyright Recticode(c) 2020.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 *
 * Created by Ali Mohammad
 ******************************************************************************/

package com.aligmohammad.doctorappclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.Place
import com.aligmohammad.doctorappclient.databinding.LabListItemBinding
import com.aligmohammad.doctorappclient.ui.fragments.doctorlist.DoctorListFragmentDirections

class PlaceListRecyclerViewAdapter(
    private val places: List<Place>,
    private val type: String = "labs"
) : RecyclerView.Adapter<PlaceListRecyclerViewAdapter.PlaceListViewHolder>(), OnMenuItemClick {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceListViewHolder {

        val binding: LabListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.lab_list_item,
            parent,
            false
        )
        binding.listener = this
        return PlaceListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceListViewHolder, position: Int) {
        holder.labListItemBinding.data = places[position]
    }

    override fun getItemCount(): Int = places.size

    inner class PlaceListViewHolder(val labListItemBinding: LabListItemBinding) :
        RecyclerView.ViewHolder(labListItemBinding.root)

    override fun onClick(v: View) {
        val lab = places.filter {
            it.name == v.tag.toString()
        }[0]

        if (type == "pharmacies") {
            Navigation.findNavController(v).navigate(DoctorListFragmentDirections.listToPharmacies())
        } else {
            Navigation.findNavController(v).navigate(DoctorListFragmentDirections.listToLabs())
        }
    }

}