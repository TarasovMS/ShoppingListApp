package com.persAssistant.shopping_list.di.viewModel.purchase

import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.CreatorPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.EditorPurchaseViewModel
import dagger.Module
import dagger.Provides

@Module
class PurchaseViewModelModule {
    @Provides
    fun provideCreatorPurchaseViewModel(purchaseInteractor: PurchaseInteractor,
                                        categoryInteractor: CategoryInteractor
    ): CreatorPurchaseViewModel {
        return CreatorPurchaseViewModel(purchaseInteractor, categoryInteractor)
    }

    @Provides
    fun provideEditorPurchaseViewModel(purchaseInteractor: PurchaseInteractor,
                                       fullPurchaseInteractor: FullPurchaseInteractor): EditorPurchaseViewModel {
        return EditorPurchaseViewModel(purchaseInteractor, fullPurchaseInteractor)
    }

    @Provides
    fun provideListOfPurchaseViewModel(purchaseInteractor: PurchaseInteractor,
                                       fullPurchaseInteractor: FullPurchaseInteractor): ListOfPurchaseViewModel {
        return ListOfPurchaseViewModel(purchaseInteractor, fullPurchaseInteractor)
    }
}
