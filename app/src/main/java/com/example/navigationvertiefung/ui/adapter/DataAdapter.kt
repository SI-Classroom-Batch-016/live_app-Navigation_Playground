package com.example.navigationvertiefung.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationvertiefung.databinding.ListItemBinding
import com.example.navigationvertiefung.ui.ListFragmentDirections


class DataAdapter(
    private val navController: NavController,
    private val dataset: List<String>
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    inner class DataViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.dataTV.text = item

        holder.binding.dataCV.setOnClickListener {
            navController.navigate(ListFragmentDirections.actionListFragmentToDetailFragment(item))
        }
    }
}