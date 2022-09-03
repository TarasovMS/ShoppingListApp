package com.persAssistant.shopping_list.feature.shopping_list.fragments

import androidx.navigation.fragment.navArgs
import com.persAssistant.shopping_list.feature.shopping_list.view_model.EditorShoppingListViewModel
import com.persAssistant.shopping_list.feature.shopping_list.view_model.ShoppingListViewModel
import javax.inject.Inject

class EditorShoppingListFragment : ShoppingListFragment() {

    @Inject
    lateinit var shoppingListViewModel: EditorShoppingListViewModel

    private val args: EditorShoppingListFragmentArgs by navArgs()

    override fun createViewModel(): ShoppingListViewModel {
        shoppingListViewModel.init(args.shoppingListId)
        return shoppingListViewModel
    }
}
