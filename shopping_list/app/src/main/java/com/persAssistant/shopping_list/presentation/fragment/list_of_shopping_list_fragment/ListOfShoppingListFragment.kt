package com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.databinding.RecyclerShoppingListBinding
import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.shopping_list.CreatorShoppingListActivity
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.EditorShoppingListActivity
import com.persAssistant.shopping_list.presentation.util.viewBinding

class ListOfShoppingListFragment: AppBaseFragment(R.layout.recycler_shopping_list) {


    private val ui: RecyclerShoppingListBinding by viewBinding (RecyclerShoppingListBinding::bind)

    private val viewModel: ListOfShoppingListViewModel by viewModels { viewModelFactory }

    private lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        // initialize Adapter
        shoppingListAdapter = ShoppingListAdapter( object: OnShoppingListClickListener {

            override fun clickedShoppingListItem(shoppingList: ShoppingList) {
                bundle.putLong(KEY_PARENT_ID, shoppingList.id!!)
                bundle.putBoolean(KEY_VISIBILITY_BUTTON, true)
                bundle.putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST.ordinal)

//                val iuRouter = UiRouter(navController = nav)
                /**
                 * здесь выполнить клик item
                 */

            }

            override fun deleteItem(shoppingList: ShoppingList) {
                viewModel.deleteItemShoppingList(shoppingList)
            }

            override fun editItem(shoppingList: ShoppingList) {
                val intent = EditorShoppingListActivity.getIntent(requireContext(), shoppingList.id!!)
                startActivity(intent)
            }
        })
        ui.recyclerViewShoppingList.adapter = shoppingListAdapter

        // initialize ViewModel
//        viewModel = app.appComponent.getListOfShoppingListViewModel()

        viewModel.deleteShoppingListId.observe(requireActivity(), androidx.lifecycle.Observer {
            shoppingListAdapter.removeShoppingList(it)
        })

        viewModel.listOfShoppingList.observe(requireActivity(), androidx.lifecycle.Observer {
            shoppingListAdapter.updateItems(it)
        })

        viewModel.init(this)

        ui.btnAddShoppingList.setOnClickListener {
            val intent = CreatorShoppingListActivity.getIntent(requireContext())
            startActivity(intent)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        app = (context.applicationContext as App)
    }
}