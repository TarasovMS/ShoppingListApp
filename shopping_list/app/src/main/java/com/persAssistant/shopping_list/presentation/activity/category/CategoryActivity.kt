package com.persAssistant.shopping_list.presentation.activity.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.persAssistant.shopping_list.databinding.FragmentCategoryBinding

abstract class CategoryActivity : AppCompatActivity() {

    companion object {
        const val KEY_CATEGORY = "CATEGORY_ID"
    }

    protected abstract fun createViewModel(): CategoryViewModel
    protected val binding: FragmentCategoryBinding by lazy { FragmentCategoryBinding.inflate(layoutInflater) }
    protected lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) {
            finish()
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}