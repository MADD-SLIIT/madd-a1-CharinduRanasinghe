package com.example.nutrishop.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutrishop.Model.CategoriesModel
import com.example.nutrishop.R
import com.example.nutrishop.databinding.ViewholderCatBinding

class CatAdapter(val items: MutableList<CategoriesModel>) :
    RecyclerView.Adapter<CatAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context


    class Viewholder(val binding: ViewholderCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderCatBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CatAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundColor(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.light_green_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white))
            )

            holder.binding.title.visibility = View.VISIBLE
        } else {
            holder.binding.pic.setBackgroundColor(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.item_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic, null)

            holder.binding.title.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size


}