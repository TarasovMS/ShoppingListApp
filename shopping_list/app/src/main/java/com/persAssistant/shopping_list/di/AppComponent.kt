package com.persAssistant.shopping_list.di

import android.content.Context
import com.persAssistant.shopping_list.di.activity.MainActivityModule
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.di.module.*
import com.persAssistant.shopping_list.di.viewModel.categories.CategoryViewModelModule
import com.persAssistant.shopping_list.di.viewModel.purchase.PurchaseViewModelModule
import com.persAssistant.shopping_list.di.viewModel.shopping_list.ShoppingListViewModelModule
import com.persAssistant.shopping_list.di.viewModel.UtilViewModelModule
import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.CreatorShoppingListViewModel
import com.persAssistant.shopping_list.presentation.activity.shopping_list.EditorShoppingListViewModel
import com.persAssistant.shopping_list.presentation.util.SUPPRESS_UNUSED
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        RoomModule::class,
        DaoModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        CategoryViewModelModule::class,
        PurchaseViewModelModule::class,
        ShoppingListViewModelModule::class,
        MainActivityModule::class,
        UtilViewModelModule::class
    ])
@Singleton
@Suppress(SUPPRESS_UNUSED)
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    //TODO delete other all methods

//    fun getRoomDataBase(): RoomDataBaseHelper

    fun getCategoryInteractor(): CategoryInteractor
//    fun getPurchaseInteractor(): PurchaseInteractor
//    fun getShoppingListInteractor(): ShoppingListInteractor
//    fun getFullPurchaseInteractor(): FullPurchaseInteractor
//
//    fun getEditorCategoryViewModel(): EditorCategoryViewModel
//    fun getCreatorCategoryViewModel(): CreatorCategoryViewModel
//    fun getListOfCategoryViewModel(): ListOfCategoryViewModel
//
//    fun getEditorPurchaseViewModel(): EditorPurchaseViewModel
//    fun getCreatorPurchaseViewModel(): CreatorPurchaseViewModel
//    fun getListOfPurchaseViewModel(): ListOfPurchaseViewModel

    fun getEditorShoppingListViewModel(): EditorShoppingListViewModel
    fun getCreatorShoppingListViewModel(): CreatorShoppingListViewModel
    fun getListOfShoppingListViewModel(): ListOfShoppingListViewModel

}