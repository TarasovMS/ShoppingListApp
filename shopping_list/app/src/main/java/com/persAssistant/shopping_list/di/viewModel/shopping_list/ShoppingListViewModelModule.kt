package com.persAssistant.shopping_list.di.viewModel.shopping_list

import com.persAssistant.shopping_list.domain.interactors_impl.ShoppingListInteractorImpl
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.CreatorShoppingListViewModel
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.EditorShoppingListViewModel
import dagger.Module
import dagger.Provides

@Module
class ShoppingListViewModelModule {
    @Provides
    fun provideCreatorShoppingListViewModel(shoppingListInteractor: ShoppingListInteractorImpl): CreatorShoppingListViewModel {
        return CreatorShoppingListViewModel(shoppingListInteractor)
    }

    @Provides
    fun provideEditorShoppingListViewModel(shoppingListInteractor: ShoppingListInteractorImpl): EditorShoppingListViewModel {
        return EditorShoppingListViewModel(shoppingListInteractor)
    }

    @Provides
    fun provideListOfShoppingListViewModel(shoppingListInteractor: ShoppingListInteractorImpl): ListOfShoppingListViewModel {
        return ListOfShoppingListViewModel(shoppingListInteractor)
    }
}
