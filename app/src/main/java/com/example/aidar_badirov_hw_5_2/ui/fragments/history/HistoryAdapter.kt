package com.example.aidar_badirov_hw_5_2.ui.fragments.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.aidar_badirov_hw_5_2.data.local.HistoryDao
import com.example.aidar_badirov_hw_5_2.data.local.HistoryEntity
import com.example.aidar_badirov_hw_5_2.databinding.ItemHistoryBinding

class HistoryAdapter(private val onItemLongListener: OnItemLongClickListener)
    : ListAdapter<HistoryEntity, HistoryAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(historyEntity: HistoryEntity) = with(binding) {
            historyEntity.apply {
                tvName1.text = firstName
                tvName2.text = secondName
                tvPercentage.text = lovePercentage
            }
                root.setOnLongClickListener {
                    onItemLongListener.onItemLongClicked(historyEntity)
                    true
                }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HistoryEntity>() {
        override fun areItemsTheSame(
            oldItem: HistoryEntity,
            newItem: HistoryEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

            override fun areContentsTheSame(
                oldItem: HistoryEntity,
                newItem: HistoryEntity
            ): Boolean {
                return oldItem.firstName == newItem.firstName
                        && oldItem.secondName == newItem.secondName
                        && oldItem.lovePercentage == newItem.lovePercentage
            }
        }
    interface OnItemLongClickListener{
        fun onItemLongClicked(historyEntity: HistoryEntity)
    }
}