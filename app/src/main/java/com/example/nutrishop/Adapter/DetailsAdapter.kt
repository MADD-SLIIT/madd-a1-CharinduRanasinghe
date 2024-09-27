package com.example.nutrishop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrishop.Model.DetailsModel
import com.example.nutrishop.databinding.ViewholderDetailsBinding

class DetailsAdapter(val items: MutableList<DetailsModel>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    private var context: Context? = null


    class ViewHolder(val binding: ViewholderDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderDetailsBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        holder.binding.nutrientTxt.text = items[position].nutrient
        holder.binding.amountTxt.text = items[position].amount


    }

    override fun getItemCount(): Int = items.size


}