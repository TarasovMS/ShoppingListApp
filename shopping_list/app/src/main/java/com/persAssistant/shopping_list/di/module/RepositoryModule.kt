package com.persAssistant.shopping_list.di.module

import com.persAssistant.shopping_list.data.repositories.CategoryRepository
import com.persAssistant.shopping_list.data.repositories.PurchaseRepository
import com.persAssistant.shopping_list.data.repositories.ShoppingListRepository
import com.persAssistant.shopping_list.data.service.CategoryService
import com.persAssistant.shopping_list.data.service.PurchaseService
import com.persAssistant.shopping_list.data.service.ShoppingListService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCategoryRepository(categoryService: CategoryService): CategoryRepository {
        return CategoryRepository(categoryService)
    }

    @Provides
    @Singleton
    fun providePurchaseRepository(purchaseService: PurchaseService): PurchaseRepository {
        return PurchaseRepository(purchaseService)
    }

    @Provides
    @Singleton
    fun provideShoppingListRepository(shoppingListService: ShoppingListService): ShoppingListRepository {
        return ShoppingListRepository(shoppingListService)
    }
}