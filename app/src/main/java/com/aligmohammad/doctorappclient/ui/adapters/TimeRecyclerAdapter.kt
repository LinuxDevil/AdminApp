package com.aligmohammad.doctorappclient.ui.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.DateTime
import com.aligmohammad.doctorappclient.databinding.TimeSingleItemBinding

class TimeRecyclerAdapter(private val times: List<DateTime>) :
    RecyclerView.Adapter<TimeRecyclerAdapter.TimeRecyclerViewHolder>() {

    private var isActive = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeRecyclerAdapter.TimeRecyclerViewHolder {
        val binding: TimeSingleItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.time_single_item,
            parent,
            false
        )
        return TimeRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TimeRecyclerAdapter.TimeRecyclerViewHolder,
        position: Int
    ) {
        holder.timeSingleItemBinding.cardView.apply {
            if (times[position].selected) {
                strokeColor = Color.BLUE
                strokeWidth = 2
            } else {
                strokeWidth = 0
            }
        }
        holder.timeSingleItemBinding.time = times[position]
        holder.timeSingleItemBinding.cardView.setOnClickListener {
            holder.timeSingleItemBinding.cardView.apply {
                holder.timeSingleItemBinding.cardView.apply {
                    if (!times[position].selected) {
                        strokeColor = Color.BLUE
                        strokeWidth = 2
                    }
                    else {
                        strokeWidth = 0
                    }
                    times[position].selected = !times[position].selected
                }
            }
        }
    }

    private fun resetAll() {
        times.map { dateTime ->
            dateTime.selected = false
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = times.size

    inner class TimeRecyclerViewHolder(val timeSingleItemBinding: TimeSingleItemBinding) :
        RecyclerView.ViewHolder(timeSingleItemBinding.root)


}