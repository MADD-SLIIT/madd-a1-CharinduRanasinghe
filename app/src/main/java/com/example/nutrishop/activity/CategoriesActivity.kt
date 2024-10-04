package com.example.nutrishop.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nutrishop.Adapter.CategoriesAdapter
import com.example.nutrishop.ViewModel.MainViewModel
import com.example.nutrishop.databinding.ActivityCategoriesBinding

class CategoriesActivity : BaseActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        categoriesAdapter = CategoriesAdapter(mutableListOf())
        binding.viewCat.layoutManager = GridLayoutManager(this, 2)
        binding.viewCat.adapter = categoriesAdapter
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun observeViewModel() {
        viewModel.categories.observe(this) { categoriesItems ->
            if (categoriesItems.isNotEmpty()) {
                categoriesAdapter.updateCategories(categoriesItems)
            } else {
                // Handle empty or null data case
            }
        }
        viewModel.loadCategory()
    }


}