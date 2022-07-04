package com.persAssistant.shopping_list.di.module

import com.persAssistant.shopping_list.data.repositories.CategoryRepository
import com.persAssistant.shopping_list.data.repositories.PurchaseRepository
import com.persAssistant.shopping_list.data.repositories.ShoppingListRepository
import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.FullPurchaseInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.ShoppingListInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideCategoryInteractor(categoryRepository: CategoryRepository): CategoryInteractorImpl {
        return CategoryInteractorImpl(categoryRepository)
    }

    @Provides
    @Singleton
    fun providePurchaseInteractor(purchaseRepository: PurchaseRepository): PurchaseInteractorImpl {
        return PurchaseInteractorImpl(purchaseRepository)
    }

    @Provides
    @Singleton
    fun provideShoppingListInteractor(shoppingListRepository: ShoppingListRepository): ShoppingListInteractorImpl {
        return ShoppingListInteractorImpl(shoppingListRepository)
    }

    @Provides
    @Singleton
    fun provideFullPurchaseInteractor(purchaseInteractor: PurchaseInteractorImpl, categoryInteractor: CategoryInteractorImpl): FullPurchaseInteractorImpl {
        return FullPurchaseInteractorImpl(purchaseInteractor, categoryInteractor)
    }
}
