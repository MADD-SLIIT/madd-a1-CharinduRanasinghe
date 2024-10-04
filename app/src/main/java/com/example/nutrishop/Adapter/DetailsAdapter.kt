package com.example.nutrishop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrishop.Model.Nutrient
import com.example.nutrishop.databinding.ViewholderDetailsBinding

class DetailsAdapter(val items: MutableList<Nutrient>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    private lateinit var context: Context



    class ViewHolder(val binding: ViewholderDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderDetailsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        val nutrient = items[position]
        holder.binding.nutrientTxt.text = nutrient.nutrient
        holder.binding.amountTxt.text = nutrient.amount



    }

    override fun getItemCount(): Int = items.size


}