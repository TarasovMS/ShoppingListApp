package com.persAssistant.shopping_list.di.viewModel.purchase

import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.FullPurchaseInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.CreatorPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.EditorPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import dagger.Module
import dagger.Provides

@Module
class PurchaseViewModelModule {
    @Provides
    fun provideCreatorPurchaseViewModel(
        purchaseInteractor: PurchaseInteractorImpl,
        categoryInteractor: CategoryInteractorImpl,
        fullPurchaseInteractor: FullPurchaseInteractorImpl
    ): CreatorPurchaseViewModel {
        return CreatorPurchaseViewModel(purchaseInteractor, categoryInteractor, fullPurchaseInteractor)
    }

    @Provides
    fun provideEditorPurchaseViewModel(
        purchaseInteractor: PurchaseInteractorImpl,
        fullPurchaseInteractor: FullPurchaseInteractorImpl
    ): EditorPurchaseViewModel {
        return EditorPurchaseViewModel(purchaseInteractor, fullPurchaseInteractor)
    }

    @Provides
    fun provideListOfPurchaseViewModel(
        purchaseInteractor: PurchaseInteractorImpl,
        fullPurchaseInteractor: FullPurchaseInteractorImpl
    ): ListOfPurchaseViewModel {
        return ListOfPurchaseViewModel(purchaseInteractor, fullPurchaseInteractor)
    }

    @Provides
    fun providePurchaseViewModel(
        fullPurchaseInteractor: FullPurchaseInteractorImpl
    ): PurchaseViewModel {
        return PurchaseViewModel(fullPurchaseInteractor)
    }

}
