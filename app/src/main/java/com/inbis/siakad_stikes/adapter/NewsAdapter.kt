package com.inbis.siakad_stikes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.data.NewsData
import com.inbis.siakad_stikes.databinding.ListNewsItemBinding

class NewsAdapter(private var newsList: List<NewsData>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ListNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsData) {
            binding.newsWritter.text = news.newsWriter
            binding.newsTitle.text = news.newsTitle
            binding.newsDate.text = news.newsDate
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val binding = ListNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}