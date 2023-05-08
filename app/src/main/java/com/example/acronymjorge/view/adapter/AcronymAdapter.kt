package com.example.acronymjorge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymjorge.databinding.ItemBinding
import com.example.acronymjorge.model.Lf

class AcronymAdapter(private val values: List<Lf?>) :
    RecyclerView.Adapter<AcronymAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item?.lf
    }
}