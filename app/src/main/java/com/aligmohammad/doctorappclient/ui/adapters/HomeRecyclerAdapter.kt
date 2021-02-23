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

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.RecyclerMenuItem
import com.aligmohammad.doctorappclient.databinding.HomeScreenMenuItemBinding

class HomeRecyclerAdapter(val items: ArrayList<RecyclerMenuItem>, val onMenuItemClick: OnMenuItemClick): RecyclerView.Adapter<HomeRecyclerAdapter.HomeRecyclerViewHolder>() {

    private var position = 0
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        context = parent.context
        val binding: HomeScreenMenuItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.home_screen_menu_item, parent, false)
        return HomeRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        this.position = position
        holder.view.menuItem = items.get(position)
        holder.view.clickListener = onMenuItemClick
    }

    override fun getItemCount(): Int = items.size

    class HomeRecyclerViewHolder(val view: HomeScreenMenuItemBinding): RecyclerView.ViewHolder(view.root)



}