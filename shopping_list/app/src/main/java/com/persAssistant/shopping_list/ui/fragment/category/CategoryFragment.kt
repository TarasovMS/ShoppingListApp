package com.persAssistant.shopping_list.ui.fragment.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentCategoryBinding
import com.persAssistant.shopping_list.ui.fragment.category.view_model.CategoryViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

abstract class CategoryFragment : AppBaseFragment(R.layout.fragment_category) {

    protected abstract fun createViewModel(): CategoryViewModel
    protected val binding: FragmentCategoryBinding by viewBinding( FragmentCategoryBinding::bind)
//    protected lateinit var viewModel: CategoryViewModel
    protected val viewModel: CategoryViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initObservers(){
        viewModel.closeEvent.observe(viewLifecycleOwner) {
            uiRouter.navigateBack()
        }
    }

    private fun initView(){
//        viewModel = createViewModel()
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@CategoryFragment
        }
    }

    companion object {
        const val KEY_CATEGORY = "CATEGORY_ID"
    }
}
