package com.example.nutrishop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.nutrishop.Model.DetailsModel
import com.example.nutrishop.databinding.ViewholderDetailsBinding

class DetailsAdapter(val items: MutableList<DetailsModel>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    private var context: Context? = null


    class ViewHolder(val binding: ViewholderDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderDetailsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        holder.binding.nutrientTxt.text = items[position].nutrient
        holder.binding.amountTxt.text = items[position].amount

        val requestOptions = RequestOptions().transform(CenterCrop())



    }

    override fun getItemCount(): Int = items.size


}