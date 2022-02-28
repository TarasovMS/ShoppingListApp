package com.persAssistant.shopping_list.presentation.activity.category.fragment

import android.os.Bundle
import android.view.View
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentCategoryBinding
import com.persAssistant.shopping_list.presentation.activity.category.CategoryViewModel
import com.persAssistant.shopping_list.presentation.util.viewBinding

abstract class CategoryFragment : AppBaseFragment(R.layout.fragment_category) {

    companion object {
        const val KEY_CATEGORY = "CATEGORY_ID"
    }

    protected abstract fun createViewModel(): CategoryViewModel
    protected val binding: FragmentCategoryBinding by viewBinding( FragmentCategoryBinding::bind)
    protected lateinit var viewModel: CategoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) { uiRouter.navigateBack()}

        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}