package com.persAssistant.shopping_list.di.fragment

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.categories.CategoryVmModule
import com.persAssistant.shopping_list.di.viewModel.categories.ListOfCategoryVmModule
import com.persAssistant.shopping_list.di.viewModel.purchase.ListOfPurchaseVmModule
import com.persAssistant.shopping_list.di.viewModel.purchase.PurchaseVmModule
import com.persAssistant.shopping_list.di.viewModel.shopping_list.ListOfShoppingListVmModule
import com.persAssistant.shopping_list.di.viewModel.shopping_list.ShoppingListVmModule
import com.persAssistant.shopping_list.presentation.activity.category.fragment.CreatorCategoryFragment
import com.persAssistant.shopping_list.presentation.activity.category.fragment.EditorCategoryFragment
import com.persAssistant.shopping_list.presentation.activity.purchase.fragment.CreatorPurchaseFragment
import com.persAssistant.shopping_list.presentation.activity.purchase.fragment.EditorPurchaseFragment
import com.persAssistant.shopping_list.presentation.activity.shopping_list.fragment.CreatorShoppingListFragment
import com.persAssistant.shopping_list.presentation.activity.shopping_list.fragment.EditorShoppingListFragment
import com.persAssistant.shopping_list.presentation.fragment.list_of_category_fragment.ListOfCategoryFragment
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseFragment
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.ListOfShoppingListFragment
import com.persAssistant.shopping_list.presentation.util.SUPPRESS_UNUSED
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

//    @FragmentScope
//    @ContributesAndroidInjector
//    abstract fun contributeAppBaseFragment(): AppBaseFragment

}
