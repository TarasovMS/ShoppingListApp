package com.persAssistant.shopping_list.feature.category.fragments

import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentCategoryBinding
import com.persAssistant.shopping_list.feature.category.view_model.CategoryViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

abstract class CategoryFragment : AppBaseFragment(R.layout.fragment_category) {

    protected abstract fun createViewModel(): CategoryViewModel
    protected val binding: FragmentCategoryBinding by viewBinding(FragmentCategoryBinding::bind)
    protected lateinit var viewModel: CategoryViewModel

    override fun initObservers() {
        viewModel.closeEvent.observe(viewLifecycleOwner) {
            uiRouter.navigateBack()
        }
    }

    override fun initUi() {
        viewModel = createViewModel()

        binding.run {
            vm = viewModel
            lifecycleOwner = this@CategoryFragment
        }
    }

}
