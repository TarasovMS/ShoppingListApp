package com.persAssistant.shopping_list.feature.shopping_list.fragments

import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.databinding.RecyclerShoppingListBinding
import com.persAssistant.shopping_list.feature.purchase.view_model.ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST
import com.persAssistant.shopping_list.feature.shopping_list.adapter.OnShoppingListClickListener
import com.persAssistant.shopping_list.feature.shopping_list.adapter.ShoppingListAdapter
import com.persAssistant.shopping_list.feature.shopping_list.view_model.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding
import com.persAssistant.shopping_list.util.getOrSet

class ListOfShoppingListFragment : AppBaseFragment(R.layout.recycler_shopping_list) {

    private val binding by viewBinding(RecyclerShoppingListBinding::bind)
    private val shoppingListAdapter by lazy { ShoppingListAdapter(shoppingListClick) }
    private val viewModel: ListOfShoppingListViewModel by viewModels { viewModelFactory }

    private val shoppingListClick = object : OnShoppingListClickListener {
        override fun clickItem(shoppingList: ShoppingList) {
            clickItemAdapter(shoppingList)
        }

        override fun deleteItem(shoppingList: ShoppingList) {
            viewModel.deleteItemShoppingList(shoppingList)
        }

        override fun editItem(shoppingList: ShoppingList) {
            editItemAdapter(shoppingList)
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
        viewModel.listOfShoppingList.observe(viewLifecycleOwner) {
            shoppingListAdapter.updateItems(it)
        }
    }

    private fun clickItemAdapter(shoppingList: ShoppingList) {
        uiRouter.navigateByDirection(
            ListOfShoppingListFragmentDirections.actionShoppingListOpeningPurchase(
                indexType = SHOPPINGLIST.ordinal.toLong(),
                parentId = shoppingList.id ?: DEFAULT_INVALID_ID,
                visibleButtonFab = true
            )
        )
    }

    private fun editItemAdapter(shoppingList: ShoppingList) {
        uiRouter.navigateByDirection(
            ListOfShoppingListFragmentDirections.actionShoppingListEditingList(
                shoppingListId = shoppingList.id.getOrSet(DEFAULT_INVALID_ID)
            )
        )
    }
}
