package com.example.nutrishop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.nutrishop.Model.ItemModel
import com.bumptech.glide.request.RequestOptions
import com.example.nutrishop.activity.DetailActivity
import com.example.nutrishop.databinding.ViewholderFruitsBinding

class FruitsAdapter(val items: MutableList<ItemModel>) :
    RecyclerView.Adapter<FruitsAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder(val binding: ViewholderFruitsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsAdapter.ViewHolder {
        context=parent.context
        val binding = ViewholderFruitsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FruitsAdapter.ViewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.descTxt.text = items[position].description
        holder.binding.priceTxt.text = "$" + items[position].price.toString()


        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl)
            .apply(requestOptions)
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateFruits(newFruits: List<ItemModel>) {
        items.clear()
        items.addAll(newFruits)
        notifyDataSetChanged()
    }
}
