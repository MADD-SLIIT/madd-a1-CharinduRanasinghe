package com.example.nutrishop.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nutrishop.Adapter.FruitsAdapter
import com.example.nutrishop.ViewModel.MainViewModel

import com.example.nutrishop.databinding.ActivityFruitsBinding

class FruitsActivity : BaseActivity() {
    private lateinit var binding: ActivityFruitsBinding
    private lateinit var fruitsAdapter: FruitsAdapter
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFruitsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        fruitsAdapter = FruitsAdapter(mutableListOf())
        binding.viewFruits.layoutManager = GridLayoutManager(this, 2)
        binding.viewFruits.adapter = fruitsAdapter
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun observeViewModel() {
        viewModel.featured.observe(this) { featuredItems ->
            if (featuredItems.isNotEmpty()) {
                fruitsAdapter.updateFruits(featuredItems)
            } else {
                // Handle empty or null data case, possibly show a message or a placeholder
            }
        }
        viewModel.loadFeatured()
    }


}