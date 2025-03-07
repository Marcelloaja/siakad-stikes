package com.inbis.siakad_stikes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ItemDayBinding
import com.inbis.siakad_stikes.model.DayItemData

class DayButtonAdapter(private val days: List<DayItemData>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<DayButtonAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DayButtonAdapter.DayViewHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding)
    }

    inner class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: DayButtonAdapter.DayViewHolder, position: Int) {
        val day = days[position]
        holder.binding.btnDay.text = day.dayName

        val context = holder.itemView.context
        if (day.isSelected) {
            holder.binding.btnDay.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.new_blue3))
            holder.binding.btnDay.setTextColor(ContextCompat.getColorStateList(context, R.color.white))
        } else {
            holder.binding.btnDay.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.white))
            holder.binding.btnDay.setTextColor(ContextCompat.getColorStateList(context, R.color.black))
        }
        holder.binding.btnDay.strokeColor = ContextCompat.getColorStateList(context, R.color.new_grey2)

        holder.binding.btnDay.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int = days.size
}