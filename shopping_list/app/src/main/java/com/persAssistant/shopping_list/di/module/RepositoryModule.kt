package com.persAssistant.shopping_list.di.module

import com.persAssistant.shopping_list.data.repositories.CategoryRepoImpl
import com.persAssistant.shopping_list.data.repositories.PurchaseRepoImpl
import com.persAssistant.shopping_list.data.repositories.ShoppingListRepoImpl
import com.persAssistant.shopping_list.data.service.CategoryService
import com.persAssistant.shopping_list.data.service.PurchaseService
import com.persAssistant.shopping_list.data.service.ShoppingListService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @[Provides Singleton]
    fun provideCategoryRepository(categoryService: CategoryService) =
        CategoryRepoImpl(categoryService)

    @[Provides Singleton]
    fun providePurchaseRepository(purchaseService: PurchaseService) =
        PurchaseRepoImpl(purchaseService)

    @[Provides Singleton]
    fun provideShoppingListRepository(shoppingListService: ShoppingListService) =
        ShoppingListRepoImpl(shoppingListService)

}
