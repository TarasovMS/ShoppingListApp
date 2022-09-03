package com.persAssistant.shopping_list.feature.category.fragments

import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.RecyclerCategoryBinding
import com.persAssistant.shopping_list.feature.category.adapter.CategoryAdapter
import com.persAssistant.shopping_list.feature.category.adapter.OnCategoryClickListener
import com.persAssistant.shopping_list.feature.category.view_model.ListOfCategoryViewModel
import com.persAssistant.shopping_list.feature.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding
import com.persAssistant.shopping_list.util.getOrSet

class ListOfCategoryFragment : AppBaseFragment(R.layout.recycler_category) {

    private val binding: RecyclerCategoryBinding by viewBinding(RecyclerCategoryBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }
    private val categoryAdapter by lazy { CategoryAdapter(categoryClick) }

    private val categoryClick = object : OnCategoryClickListener {
        override fun clickedCategoryItem(category: Category) {
            clickItemAdapter(category)
        }

        override fun deleteItem(category: Category) {
            viewModel.deleteItemCategory(category)
        }

        override fun editItem(category: Category) {
            editItemAdapter(category)
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
//            deleteCategoryId.observe(requireActivity()) {
//                categoryAdapter.removeCategory(it)
//            }
//
            categoryList.observe(requireActivity()) {
                categoryAdapter.updateItems(it)
            }
        }
    }

    private fun clickItemAdapter(category: Category) {
        uiRouter.navigateByDirection(
            ListOfCategoryFragmentDirections.actionCategoryOpeningPurchase(
                indexType = ListOfPurchaseViewModel.IdTypes.CATEGORY.ordinal.toLong(),
                parentId = category.id.getOrSet(DEFAULT_CATEGORIES_COUNT),
                visibleButtonFab = false
            )
        )
    }

    private fun editItemAdapter(category: Category) {
        uiRouter.navigateByDirection(
            ListOfCategoryFragmentDirections.actionEditingCategory(
                categoryId = category.id.getOrSet(DEFAULT_CATEGORIES_COUNT)
            )
        )
    }
}
