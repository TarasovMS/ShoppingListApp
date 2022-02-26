package com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.databinding.RecyclerShoppingListBinding
import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.shopping_list.CreatorShoppingListActivity
import com.persAssistant.shopping_list.presentation.activity.shopping_list.ShoppingListActivity
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.presentation.util.viewBinding

class  ListOfShoppingListFragment: AppBaseFragment(R.layout.recycler_shopping_list) {

    private val binding: RecyclerShoppingListBinding by viewBinding (RecyclerShoppingListBinding::bind)
    private val viewModel: ListOfShoppingListViewModel by viewModels { viewModelFactory }
    private lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingListAdapter = ShoppingListAdapter( object: OnShoppingListClickListener {
            override fun clickedShoppingListItem(shoppingList: ShoppingList) {
                val bundle = Bundle()
                bundle.putLong(KEY_PARENT_ID, shoppingList.id!!)
                bundle.putBoolean(KEY_VISIBILITY_BUTTON, true)
                bundle.putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST.ordinal)

                Log.d("ListPurchaseAfterShopp"," parentId = ${shoppingList.id}, " +
                        "visibility = " +
                        "true, " +
                        "idTypeIndex = ${ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST.ordinal}  ")

                uiRouter.navigateById(R.id.purchaseList,bundle)
            }

            override fun deleteItem(shoppingList: ShoppingList) {
                viewModel.deleteItemShoppingList(shoppingList)
            }

            override fun editItem(shoppingList: ShoppingList) {
//                val intent = EditorShoppingListActivity.getIntent(requireContext(), shoppingList.id!!)
//                startActivity(intent)
                val bundle = Bundle()
                bundle.putLong(ShoppingListActivity.SHOPPING_LIST_KEY, shoppingList.id!!)
                uiRouter.navigateById(R.id.editShoppingList,bundle)
            }
        })

        binding.recyclerViewShoppingList.adapter = shoppingListAdapter

        viewModel.deleteShoppingListId.observe(requireActivity()) {
            shoppingListAdapter.removeShoppingList(it)
        }

        viewModel.listOfShoppingList.observe(requireActivity()) {
            shoppingListAdapter.updateItems(it)
        }

        viewModel.init(this)

        binding.btnAddShoppingList.setOnClickListener {
//            val intent = CreatorShoppingListActivity.getIntent(requireContext())
//            startActivity(intent)
            uiRouter.navigateById(R.id.createShoppingList)
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        app = (context.applicationContext as App)
//    }
}