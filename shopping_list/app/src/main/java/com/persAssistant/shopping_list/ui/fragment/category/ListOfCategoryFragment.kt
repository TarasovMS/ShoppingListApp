package com.persAssistant.shopping_list.ui.fragment.category

import android.os.Bundle
import android.view.View
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

    //TODO избавиться от lateinit через делегат и !!
    private val binding: RecyclerCategoryBinding by viewBinding(RecyclerCategoryBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObserve()
    }

    private fun init() {
        categoryAdapter = CategoryAdapter(object : OnCategoryClickListener {
            override fun clickedCategoryItem(category: Category) {
                val bundle = Bundle().apply {
                    putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.CATEGORY.ordinal)
                    putLong(KEY_PARENT_ID, category.id!!)
                    putBoolean(KEY_VISIBILITY_BUTTON, false)
                }

                uiRouter.navigateById(R.id.purchaseList, bundle)
            }

            override fun deleteItem(category: Category) {
                viewModel.deleteItemCategory(category)
            }

            override fun editItem(category: Category) {
                val bundle = Bundle()
                bundle.putLong(KEY_CATEGORY, category.id!!)
                uiRouter.navigateById(R.id.editCategory, bundle)
            }
        })

        viewModel.init(this@ListOfCategoryFragment)

        binding.apply {
            recyclerViewCategory.adapter = categoryAdapter

            recyclerCategoryBtnAdd.setOnClickListener {
                uiRouter.navigateById(R.id.createCategory)
            }
        }
    }

    private fun initObserve() {
        viewModel.deleteCategoryId.observe(requireActivity()) {
            categoryAdapter.removeCategory(it)
        }

        viewModel.categoryList.observe(requireActivity()) {
            categoryAdapter.updateItems(it)
        }
    }
}
