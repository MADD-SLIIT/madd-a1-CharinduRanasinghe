package com.example.nutrishop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.nutrishop.Model.CategoriesModel
import com.example.nutrishop.databinding.ViewholderCategoryBinding

class CategoriesAdapter(val items: MutableList<CategoriesModel>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        context=parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title


        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

    }

    override fun getItemCount(): Int = items.size

    fun updateCategories(newCategories: List<CategoriesModel>) {
        items.clear()
        items.addAll(newCategories)
        notifyDataSetChanged()
    }
}
