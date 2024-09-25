package com.example.nutrishop.activity

import android.os.Bundle
import android.view.View
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
import com.example.nutrishop.ViewModel.MainViewModel
import com.example.nutrishop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initBanner()
        initCategory()
        initFeatured()
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