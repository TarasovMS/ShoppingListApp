package com.persAssistant.shopping_list.ui.fragment.shopping_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.databinding.RecyclerShoppingListBinding
import com.persAssistant.shopping_list.ui.fragment.shopping_list.ShoppingListFragment.Companion.SHOPPING_LIST_KEY
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

class ListOfShoppingListFragment : AppBaseFragment(R.layout.recycler_shopping_list) {

    //TODO избавиться  !!
    private val shoppingListAdapter by lazy { ShoppingListAdapter(shoppingListClick) }
    private val binding: RecyclerShoppingListBinding by viewBinding(RecyclerShoppingListBinding::bind)
    private val viewModel: ListOfShoppingListViewModel by viewModels { viewModelFactory }

    private val shoppingListClick = object : OnShoppingListClickListener {
        override fun clickedShoppingListItem(shoppingList: ShoppingList) {
            uiRouter.navigateById(R.id.purchaseList, Bundle().apply {
                putLong(KEY_PARENT_ID, shoppingList.id!!)
                putBoolean(KEY_VISIBILITY_BUTTON, true)
                putInt(KEY_INDEX_TYPE, SHOPPINGLIST.ordinal)
            })
        }

        override fun deleteItem(shoppingList: ShoppingList) {
            viewModel.deleteItemShoppingList(shoppingList)
        }

        override fun editItem(shoppingList: ShoppingList) {
            uiRouter.navigateById(R.id.editShoppingList, Bundle().apply {
                putLong(SHOPPING_LIST_KEY, shoppingList.id!!)
            })
        }
    }

    override fun initUi() {
        viewModel.init(this@ListOfShoppingListFragment)
        binding.recyclerViewShoppingList.adapter = shoppingListAdapter
    }

    override fun initListeners() {
        binding.recyclerShoppingListBtnAdd.setOnClickListener {
            uiRouter.navigateById(R.id.createShoppingList)
        }
    }

    override fun initObservers() {
        viewModel.run {
            listOfShoppingList.observe(viewLifecycleOwner) {
                shoppingListAdapter.updateItems(it)
            }

            deleteShoppingListId.observe(viewLifecycleOwner) {
                shoppingListAdapter.removeShoppingList(it)
            }
        }
    }
}
