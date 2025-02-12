package com.inbis.siakad_stikes.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.R

class ImageAdapter(private val imagePairs: List<Pair<Int, Int>>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView1: ImageView = view.findViewById(R.id.imageView1)
        val imageView2: ImageView = view.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val (image1, image2) = imagePairs[position]
        holder.imageView1.setImageResource(image1)
        holder.imageView2.setImageResource(image2)
    }

    override fun getItemCount(): Int = imagePairs.size
}
