package com.example.nutrishop.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.nutrishop.Adapter.CatAdapter
import com.example.nutrishop.Adapter.FeaturedAdapter
import com.example.nutrishop.Model.SliderModel
import com.example.nutrishop.Adapter.SliderAdapter
import com.example.nutrishop.R
import com.example.nutrishop.ViewModel.MainViewModel
import com.example.nutrishop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchButton: ImageView
    private lateinit var searchInput: EditText
    private var isSearchOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        searchButton = findViewById(R.id.search_btn)
        searchInput = findViewById(R.id.search_input)

        searchButton.setOnClickListener {
            toggleSearch()
        }

        findViewById<TextView>(R.id.menu_loader1).setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }



        initBanner()
        initCategory()
        initFeatured()
        initBottomMenu()
    }

    private fun toggleSearch() {
        if (isSearchOpen) {

            ObjectAnimator.ofFloat(searchInput, "alpha", 1f, 0f).apply {
                duration = 300
                start()
            }.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    searchInput.visibility = View.GONE
                }
            })
        } else {

            searchInput.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(searchInput, "alpha", 0f, 1f).apply {
                duration = 300
                start()
            }
        }
        isSearchOpen = !isSearchOpen
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener { startActivity(Intent(this@MainActivity, CartActivity::class.java)) }
        binding.filterBtn.setOnClickListener { startActivity(Intent(this@MainActivity, FilterActivity::class.java)) }
        binding.userBtn.setOnClickListener { startActivity(Intent(this@MainActivity, ProfileActivity::class.java)) }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }

    private fun banners(images: List<SliderModel>) {
        binding.viewpagerSlider.adapter = SliderAdapter(images, binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))

        }
        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1) {
            binding.dotindicator.visibility = View.VISIBLE
            binding.dotindicator.attachTo(binding.viewpagerSlider)
        }


    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.categories.observe(this, Observer {
            binding.viewCategory.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter = CatAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }

    private fun initFeatured() {
        binding.progressBarProducts.visibility = View.VISIBLE
        viewModel.featured.observe(this, Observer {
            binding.viewFeatured.layoutManager = GridLayoutManager(this@MainActivity,2)
            binding.viewFeatured.adapter = FeaturedAdapter(it)
            binding.progressBarProducts.visibility = View.GONE
        })
        viewModel.loadFeatured()
    }


}