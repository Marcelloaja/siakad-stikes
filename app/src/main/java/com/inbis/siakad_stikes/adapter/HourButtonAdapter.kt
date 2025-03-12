package com.inbis.siakad_stikes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ItemHoursBinding
import com.inbis.siakad_stikes.model.HourItemData

class HourButtonAdapter(private val hours: List<HourItemData>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<HourButtonAdapter.HourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val binding = ItemHoursBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourViewHolder(binding)
    }

    inner class HourViewHolder(val binding: ItemHoursBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        val hour = hours[position]
        holder.binding.btnHour.text = hour.hourName

        val context = holder.itemView.context
        if (hour.isSelected) {
            holder.binding.btnHour.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.new_blue3))
            holder.binding.btnHour.setTextColor(ContextCompat.getColorStateList(context, R.color.white))
            holder.binding.btnHour.strokeWidth = 0
        } else {
            holder.binding.btnHour.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.white))
            holder.binding.btnHour.strokeColor = ContextCompat.getColorStateList(context, R.color.new_grey2)
            holder.binding.btnHour.strokeWidth = 3
            holder.binding.btnHour.setTextColor(ContextCompat.getColorStateList(context, R.color.black))
        }

        holder.binding.btnHour.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int = hours.size
}
