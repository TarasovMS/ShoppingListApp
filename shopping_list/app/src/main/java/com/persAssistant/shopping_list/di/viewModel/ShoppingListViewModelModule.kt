package com.persAssistant.shopping_list.di.viewModel

import com.persAssistant.shopping_list.domain.interactors.ShoppingListInteractor
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.CreatorShoppingListViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.EditorShoppingListViewModel
import dagger.Module
import dagger.Provides

@Module
class ShoppingListViewModelModule {
    @Provides
    fun provideCreatorShoppingListViewModel(shoppingListInteractor: ShoppingListInteractor): CreatorShoppingListViewModel {
        return CreatorShoppingListViewModel(shoppingListInteractor)
    }

    @Provides
    fun provideEditorShoppingListViewModel(shoppingListInteractor: ShoppingListInteractor): EditorShoppingListViewModel {
        return EditorShoppingListViewModel(shoppingListInteractor)
    }

    @Provides
    fun provideListOfShoppingListViewModel(shoppingListInteractor: ShoppingListInteractor): ListOfShoppingListViewModel {
        return ListOfShoppingListViewModel(shoppingListInteractor)
    }
}