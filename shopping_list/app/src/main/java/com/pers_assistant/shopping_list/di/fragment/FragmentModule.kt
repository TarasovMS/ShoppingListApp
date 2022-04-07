package com.pers_assistant.shopping_list.di.fragment

import com.pers_assistant.shopping_list.di.scopes.FragmentScope
import com.pers_assistant.shopping_list.di.viewModel.categories.CategoryVmModule
import com.pers_assistant.shopping_list.di.viewModel.categories.ListOfCategoryVmModule
import com.pers_assistant.shopping_list.di.viewModel.purchase.ListOfPurchaseVmModule
import com.pers_assistant.shopping_list.di.viewModel.purchase.PurchaseVmModule
import com.pers_assistant.shopping_list.di.viewModel.shopping_list.ListOfShoppingListVmModule
import com.pers_assistant.shopping_list.di.viewModel.shopping_list.ShoppingListVmModule
import com.pers_assistant.shopping_list.ui.fragment.category.CreatorCategoryFragment
import com.pers_assistant.shopping_list.ui.fragment.category.EditorCategoryFragment
import com.pers_assistant.shopping_list.ui.fragment.purchase.CreatorPurchaseFragment
import com.pers_assistant.shopping_list.ui.fragment.purchase.EditorPurchaseFragment
import com.pers_assistant.shopping_list.ui.fragment.shopping_list.CreatorShoppingListFragment
import com.pers_assistant.shopping_list.ui.fragment.shopping_list.EditorShoppingListFragment
import com.pers_assistant.shopping_list.ui.fragment.category.ListOfCategoryFragment
import com.pers_assistant.shopping_list.ui.fragment.purchase.ListOfPurchaseFragment
import com.pers_assistant.shopping_list.ui.fragment.shopping_list.ListOfShoppingListFragment
import com.pers_assistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress(SUPPRESS_UNUSED)
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ListOfCategoryVmModule::class
        ]
    )
    abstract fun contributeListOfCategoryFragment(): ListOfCategoryFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ListOfShoppingListVmModule::class
        ]
    )
    abstract fun contributeListOfShoppingListFragment(): ListOfShoppingListFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ListOfPurchaseVmModule::class
        ]
    )
    abstract fun contributeListOfPurchaseFragment(): ListOfPurchaseFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            PurchaseVmModule::class
        ]
    )
    abstract fun contributeEditorPurchaseFragment(): EditorPurchaseFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            PurchaseVmModule::class
        ]
    )
    abstract fun contributeCreatorPurchaseFragment(): CreatorPurchaseFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            CategoryVmModule::class
        ]
    )
    abstract fun contributeCreatorCategoryFragment(): CreatorCategoryFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            CategoryVmModule::class
        ]
    )
    abstract fun contributeEditorCategoryFragment(): EditorCategoryFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ShoppingListVmModule::class
        ]
    )
    abstract fun contributeEditorShoppingListFragment(): EditorShoppingListFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ShoppingListVmModule::class
        ]
    )
    abstract fun contributeCreatorShoppingListFragment(): CreatorShoppingListFragment


//    @Binds
//    @IntoMap
//    @FragmentScope
//    @ContributesAndroidInjector
//    abstract fun contributeSelectionOfCategoryInDialog(): SelectionOfCategoryInDialog

}
