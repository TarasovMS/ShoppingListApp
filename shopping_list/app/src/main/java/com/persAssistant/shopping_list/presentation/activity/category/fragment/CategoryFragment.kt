package com.persAssistant.shopping_list.presentation.activity.category.fragment

import android.os.Bundle
import android.view.View
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.ActivityCategoryBinding
import com.persAssistant.shopping_list.presentation.activity.category.CategoryViewModel

abstract class CategoryFragment : AppBaseFragment(R.layout.activity_category) {

    companion object {
        const val KEY_CATEGORY = "CATEGORY_ID"
    }

    protected abstract fun createViewModel(): CategoryViewModel
    protected val binding: ActivityCategoryBinding by lazy { ActivityCategoryBinding.inflate(layoutInflater) }
    protected lateinit var viewModel: CategoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) {
            // TODO сигнал о закрытия Edited и Created
//            finish()
            uiRouter.navigateById(R.id.recyclerView_category)

        }

        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}