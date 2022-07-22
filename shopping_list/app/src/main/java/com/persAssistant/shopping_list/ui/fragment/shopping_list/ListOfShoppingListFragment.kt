package com.persAssistant.shopping_list.ui.fragment.shopping_list

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.INVALID_ID
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.databinding.RecyclerShoppingListBinding
import com.persAssistant.shopping_list.ui.fragment.shopping_list.ShoppingListFragment.Companion.SHOPPING_LIST_KEY
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

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
        viewModel.run {
            listOfShoppingList.observe(viewLifecycleOwner) {
                shoppingListAdapter.updateItems(it)
            }

            deleteShoppingListId.observe(viewLifecycleOwner) {
                shoppingListAdapter.removeShoppingList(it)
            }
        }
    }

    private fun clickItemAdapter(shoppingList: ShoppingList) {
//        uiRouter.navigateById(R.id.purchaseList, Bundle().apply {
//            putInt(KEY_INDEX_TYPE, SHOPPINGLIST.ordinal)
//            putLong(KEY_PARENT_ID, shoppingList.id ?: INVALID_ID)
//            putBoolean(KEY_VISIBILITY_BUTTON, true)
//        })

        uiRouter.navigateByDirection(
            ListOfShoppingListFragmentDirections.actionShoppingListOpeningPurchase(
                indexType = SHOPPINGLIST.ordinal.toLong(),
                parentId = shoppingList.id ?: INVALID_ID,
                visibleButtonFab = true
            )
        )
    }

    private fun editItemAdapter(shoppingList: ShoppingList) {
        uiRouter.navigateById(R.id.editShoppingList, Bundle().apply {
            putLong(SHOPPING_LIST_KEY, shoppingList.id ?: INVALID_ID)
        })
    }
}
