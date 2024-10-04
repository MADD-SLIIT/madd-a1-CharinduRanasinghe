package com.example.nutrishop.activity

import ManagmentCart
import android.content.Intent
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nutrishop.Adapter.DetailsAdapter
import com.example.nutrishop.Model.ItemModel
import com.example.nutrishop.R
import com.example.nutrishop.databinding.ActivityDetailBinding


class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)

        getBundle()
        initLists()

    }

    private fun initLists() {

        binding.nutList.adapter = DetailsAdapter(item.details)
        binding.nutList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }



    private fun getBundle() {
        item = intent.getParcelableExtra("object") ?: return // Safely unwrap the parcelable item

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$"+ item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        setupClickListeners()

        Glide.with(this)
            .load(item.picUrl)
            .into(binding.pic)
    }

    private fun setupClickListeners() {
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertFood(item)
        }
        binding.backBtn.setOnClickListener { finish() }

        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }
    }
}