package com.persAssistant.shopping_list.di.fragment

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ListOfCategoryVmModule
import com.persAssistant.shopping_list.di.viewModel.ListOfPurchaseVmModule
import com.persAssistant.shopping_list.di.viewModel.ListOfShoppingListVmModule
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


//    @FragmentScope
//    @ContributesAndroidInjector
//    abstract fun contributeAppBaseFragment(): AppBaseFragment

}
