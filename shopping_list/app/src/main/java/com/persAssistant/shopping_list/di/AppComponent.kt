package com.persAssistant.shopping_list.di

import com.persAssistant.shopping_list.data.database.RoomDataBaseHelper
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors.ShoppingListInteractor
import com.persAssistant.shopping_list.di.module.*
import com.persAssistant.shopping_list.di.module.viewModelModule.CategoryViewModelModule
import com.persAssistant.shopping_list.di.module.viewModelModule.PurchaseViewModelModule
import com.persAssistant.shopping_list.di.module.viewModelModule.ShoppingListViewModelModule
import com.persAssistant.shopping_list.presentation.activity.category.CreatorCategoryViewModel
import com.persAssistant.shopping_list.presentation.activity.category.EditorCategoryViewModel
import com.persAssistant.shopping_list.presentation.fragment.list_of_category_fragment.ListOfCategoryViewModel
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.presentation.activity.purchase.CreatorPurchaseViewModel
import com.persAssistant.shopping_list.presentation.activity.purchase.EditorPurchaseViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.CreatorShoppingListViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.EditorShoppingListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RoomModule::class,
        DaoModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        CategoryViewModelModule::class,
        PurchaseViewModelModule::class,
        ShoppingListViewModelModule::class
    ])

interface AppComponent {
    fun getCategoryInteractor(): CategoryInteractor
    fun getPurchaseInteractor(): PurchaseInteractor
    fun getShoppingListInteractor(): ShoppingListInteractor
    fun getFullPurchaseInteractor(): FullPurchaseInteractor
    fun getRoomDataBase(): RoomDataBaseHelper

    fun getEditorCategoryViewModel(): EditorCategoryViewModel
    fun getCreatorCategoryViewModel(): CreatorCategoryViewModel
    fun getListOfCategoryViewModel(): ListOfCategoryViewModel

    fun getEditorPurchaseViewModel(): EditorPurchaseViewModel
    fun getCreatorPurchaseViewModel(): CreatorPurchaseViewModel
    fun getListOfPurchaseViewModel(): ListOfPurchaseViewModel

    fun getEditorShoppingListViewModel(): EditorShoppingListViewModel
    fun getCreatorShoppingListViewModel(): CreatorShoppingListViewModel
    fun getListOfShoppingListViewModel(): ListOfShoppingListViewModel

}