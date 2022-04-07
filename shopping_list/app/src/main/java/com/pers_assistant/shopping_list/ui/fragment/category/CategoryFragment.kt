package com.pers_assistant.shopping_list.ui.fragment.category

import android.os.Bundle
import android.view.View
import com.pers_assistant.shopping_list.R
import com.pers_assistant.shopping_list.base.AppBaseFragment
import com.pers_assistant.shopping_list.databinding.FragmentCategoryBinding
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.CategoryViewModel
import com.pers_assistant.shopping_list.util.viewBinding

abstract class CategoryFragment : AppBaseFragment(R.layout.fragment_category) {

    companion object {
        const val KEY_CATEGORY = "CATEGORY_ID"
    }

    protected abstract fun createViewModel(): CategoryViewModel
    protected val binding: FragmentCategoryBinding by viewBinding( FragmentCategoryBinding::bind)
    protected lateinit var viewModel: CategoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * TODO viewModel.apply{} ??
         */

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) { uiRouter.navigateBack()}

        binding.apply {
            vm = viewModel
            lifecycleOwner = this@CategoryFragment
        }
    }
}