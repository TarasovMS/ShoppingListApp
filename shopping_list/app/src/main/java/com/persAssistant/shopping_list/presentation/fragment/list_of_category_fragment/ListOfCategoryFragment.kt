package com.persAssistant.shopping_list.presentation.fragment.list_of_category_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.RecyclerCategoryBinding
import com.persAssistant.shopping_list.presentation.activity.category.fragment.CategoryFragment.Companion.KEY_CATEGORY
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.presentation.util.viewBinding

class ListOfCategoryFragment: AppBaseFragment(R.layout.recycler_category) {

    private val binding: RecyclerCategoryBinding by viewBinding(RecyclerCategoryBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = CategoryAdapter (object : OnCategoryClickListener {
            override fun clickedCategoryItem(category: Category) {
                val bundle = Bundle()
                bundle.putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.CATEGORY.ordinal)
                bundle.putLong(KEY_PARENT_ID, category.id!!)
                bundle.putBoolean(KEY_VISIBILITY_BUTTON, false)

                uiRouter.navigateById(R.id.purchaseList,bundle)
            }

            override fun deleteItem(category: Category) { viewModel.deleteItemCategory(category) }

            override fun editItem(category: Category) {
                val bundle = Bundle()
                bundle.putLong(KEY_CATEGORY, category.id!!)
                uiRouter.navigateById(R.id.editCategory,bundle)
            }
        })
        binding.recyclerViewCategory.adapter = categoryAdapter

        viewModel.deleteCategoryId.observe(requireActivity()){
            categoryAdapter.removeCategory(it)
        }

        viewModel.categoryList.observe(requireActivity()) {
            categoryAdapter.updateItems(it)
        }

        viewModel.init(this)

        binding.btnAddCategory.setOnClickListener {
            uiRouter.navigateById(R.id.createCategory)
        }
    }
}