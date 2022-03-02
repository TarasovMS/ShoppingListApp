package com.persAssistant.shopping_list.presentation.fragment.shopping_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.databinding.RecyclerShoppingListBinding
import com.persAssistant.shopping_list.presentation.fragment.shopping_list.ShoppingListFragment.Companion.SHOPPING_LIST_KEY
import com.persAssistant.shopping_list.presentation.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.presentation.fragment.shopping_list.view_model.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.presentation.util.viewBinding

class ListOfShoppingListFragment : AppBaseFragment(R.layout.recycler_shopping_list) {

    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private val binding: RecyclerShoppingListBinding by viewBinding(RecyclerShoppingListBinding::bind)
    private val viewModel: ListOfShoppingListViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingListAdapter = ShoppingListAdapter(object : OnShoppingListClickListener {
            override fun clickedShoppingListItem(shoppingList: ShoppingList) {
                val bundle = Bundle()

                bundle.apply {
                    putLong(KEY_PARENT_ID, shoppingList.id!!)
                    putBoolean(KEY_VISIBILITY_BUTTON, true)
                    putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST.ordinal)
                }

                uiRouter.navigateById(R.id.purchaseList, bundle)
            }

            override fun deleteItem(shoppingList: ShoppingList) {
                viewModel.deleteItemShoppingList(shoppingList)
            }

            override fun editItem(shoppingList: ShoppingList) {
                val bundle = Bundle()
                bundle.putLong(SHOPPING_LIST_KEY, shoppingList.id!!)
                uiRouter.navigateById(R.id.editShoppingList, bundle)
            }
        })

        binding.apply {
            recyclerViewShoppingList.adapter = shoppingListAdapter

            btnAddShoppingList.setOnClickListener {
                uiRouter.navigateById(R.id.createShoppingList)
            }
        }

        viewModel.apply {
            init(this@ListOfShoppingListFragment)
            listOfShoppingList.observe(requireActivity()) { shoppingListAdapter.updateItems(it) }

            deleteShoppingListId.observe(requireActivity()) {
                shoppingListAdapter.removeShoppingList(it)
            }
        }
    }
}