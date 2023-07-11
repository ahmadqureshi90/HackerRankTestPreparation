package com.example.hackerranktestpreparation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackerranktestpreparation.data.Item
import com.example.hackerranktestpreparation.databinding.ItemRecyclerViewBinding

class DataListAdapter (val context: Context, private val dataList: List<Item>): RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.binding.apply {
            tvItemHeading.text = dataList[position].description
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class DataViewHolder(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)
}