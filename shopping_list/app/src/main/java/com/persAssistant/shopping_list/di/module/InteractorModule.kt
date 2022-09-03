package com.persAssistant.shopping_list.di.module

import com.persAssistant.shopping_list.common.validation_handler.ValidateHandler
import com.persAssistant.shopping_list.data.repositories.CategoryRepoImpl
import com.persAssistant.shopping_list.data.repositories.PurchaseRepoImpl
import com.persAssistant.shopping_list.data.repositories.ShoppingListRepoImpl
import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.FullPurchaseInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.ShoppingListInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @[Provides Singleton]
    fun provideCategoryInteractor(categoryRepository: CategoryRepoImpl) =
        CategoryInteractorImpl(categoryRepository)

    @[Provides Singleton]
    fun providePurchaseInteractor(purchaseRepository: PurchaseRepoImpl) =
        PurchaseInteractorImpl(purchaseRepository)

    @[Provides Singleton]
    fun provideShoppingListInteractor(shoppingListRepository: ShoppingListRepoImpl) =
        ShoppingListInteractorImpl(shoppingListRepository)

    @[Provides Singleton]
    fun provideFullPurchaseInteractor(
        purchaseInteractor: PurchaseInteractorImpl,
        categoryInteractor: CategoryInteractorImpl,
        validateHandler: ValidateHandler,
    ) = FullPurchaseInteractorImpl(
        purchaseInteractor,
        categoryInteractor,
        validateHandler
    )

}
