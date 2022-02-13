package com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.persAssistant.shopping_list.base.BaseFragment
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.databinding.RecyclerShoppingListBinding
import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.EditorShoppingListActivity
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.OnShoppingListClickListener
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.ShoppingListAdapter
import java.util.*

class ListOfShoppingListFragment: BaseFragment() {

    private lateinit var viewModel: ListOfShoppingListViewModel
    private lateinit var ui: RecyclerShoppingListBinding

    private lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ui = RecyclerShoppingListBinding.inflate(layoutInflater)

        val bundle = Bundle()

        // initialize Adapter
        shoppingListAdapter = ShoppingListAdapter(LinkedList(), object:
            OnShoppingListClickListener {
            override fun clickedShoppingListItem(shoppingList: ShoppingList) {
//                val intent = ListOfPurchaseActivity.getIntentByShoppingListId(requireContext(),shoppingList.id!!)
//                val intent = ListOfPurchaseFragment.getIntentByShoppingListId(requireActivity().intent, )
//                startActivity(intent)
//                val KEY_INDEX_TYPE = "INDEX_TYPE"
//                val KEY_PARENT_ID = "PARENT_ID"
//                val KEY_VISIBILITY_BUTTON = "VISIBILITY_BUTTON"

                bundle.putLong(KEY_PARENT_ID, shoppingList.id!!)
                bundle.putBoolean(KEY_VISIBILITY_BUTTON, true)
                bundle.putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.SHOPPINGLIST.ordinal)

//                val fragmentManager = childFragmentManager.beginTransaction()
//                fragmentManager.replace(R.id.fragment, ListOfPurchaseFragment())
//                fragmentManager.commit()

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
        viewModel = app.appComponent.getListOfShoppingListViewModel()

        viewModel.deleteShoppingListId.observe(requireActivity(), androidx.lifecycle.Observer {
            shoppingListAdapter.removeShoppingList(it)
        })

        viewModel.listOfShoppingList.observe(requireActivity(), androidx.lifecycle.Observer {
            shoppingListAdapter.updateItems(it)
        })

        viewModel.init(this)

//        ui.btnAddShoppingList.setOnClickListener {
//            val intent = CreatorShoppingListActivity.getIntent(requireContext())
//            startActivity(intent)
//        }
        
        return ui.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        app = (context.applicationContext as App)
    }
}