package com.example.twitter_trending_android.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_trending_android.databinding.TrendListItemBinding

class TrendAdapter : ListAdapter<TrendViewState, TrendAdapter.TrendViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding = TrendListItemBinding.inflate(LayoutInflater.from(parent.context))
        return TrendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TrendViewHolder(private val binding: TrendListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trend: TrendViewState) {
            binding.apply {
                trendName.text = trend.name
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<TrendViewState>() {
        override fun areItemsTheSame(oldItem: TrendViewState, newItem: TrendViewState): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TrendViewState, newItem: TrendViewState) = oldItem == newItem
    }
}
