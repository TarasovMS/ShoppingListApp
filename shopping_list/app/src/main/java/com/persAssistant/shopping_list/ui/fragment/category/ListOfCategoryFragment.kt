package com.persAssistant.shopping_list.ui.fragment.category

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.RecyclerCategoryBinding
import com.persAssistant.shopping_list.ui.fragment.category.CategoryFragment.Companion.KEY_CATEGORY
import com.persAssistant.shopping_list.ui.fragment.category.view_model.ListOfCategoryViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

class ListOfCategoryFragment : AppBaseFragment(R.layout.recycler_category) {

    //TODO избавиться от !!
    private val binding: RecyclerCategoryBinding by viewBinding(RecyclerCategoryBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }
    private val categoryAdapter by lazy { CategoryAdapter(categoryClick) }

    private val categoryClick = object : OnCategoryClickListener {
        override fun clickedCategoryItem(category: Category) {
            uiRouter.navigateById(R.id.purchaseList, Bundle().apply {
                putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.CATEGORY.ordinal)
                putLong(KEY_PARENT_ID, category.id!!)
                putBoolean(KEY_VISIBILITY_BUTTON, false)
            })
        }

        override fun deleteItem(category: Category) {
            viewModel.deleteItemCategory(category)
        }

        override fun editItem(category: Category) {
            uiRouter.navigateById(R.id.editCategory, Bundle().apply {
                putLong(KEY_CATEGORY, category.id!!)
            })
        }
    }

    override fun initUi() {
        binding.recyclerViewCategory.adapter = categoryAdapter
        viewModel.init(this@ListOfCategoryFragment)
    }

    override fun initListeners() {
        binding.recyclerCategoryBtnAdd.setOnClickListener {
            uiRouter.navigateById(R.id.createCategory)
        }
    }

    override fun initObservers() {
        viewModel.run {
            deleteCategoryId.observe(requireActivity()) {
                categoryAdapter.removeCategory(it)
            }

            categoryList.observe(requireActivity()) {
                categoryAdapter.updateItems(it)
            }
        }
    }
}
