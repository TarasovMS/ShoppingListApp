package com.persAssistant.shopping_list.feature.purchase.view_model

import com.persAssistant.shopping_list.common.AppBaseViewModel.IsCompletedState.ACTIVE
import com.persAssistant.shopping_list.common.EMPTY_STRING
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.common.PRICE_DEFAULT_DOUBLE
import com.persAssistant.shopping_list.common.QUANTITY_DEFAULT_ONE_STRING
import com.persAssistant.shopping_list.util.getOrSet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatorPurchaseViewModel @Inject constructor(
    val purchaseInteractor: PurchaseInteractorImpl,
    val categoryInteractor: CategoryInteractorImpl,
    override val fullPurchaseInteractor: FullPurchaseInteractor,
) : PurchaseViewModel(fullPurchaseInteractor) {

    fun init(shoppingListId: Long) {
        listId.value = shoppingListId
    }

    override fun onClickButtonSavePurchase() {
        saveData()
    }

    private fun saveData() {
        val purchase = Purchase(
            name = name.value.orEmpty(),
            categoryId = categoryId.value.getOrSet(DEFAULT_CATEGORIES_COUNT),
            listId = listId.value.getOrSet(DEFAULT_INVALID_ID),
            price = checkPrice(price.value.getOrSet(EMPTY_STRING)),
            unit = unit.value.orEmpty(),
            quantity = quantity.value.getOrSet(QUANTITY_DEFAULT_ONE_STRING),
            isCompleted = isCompleted.value.getOrSet(ACTIVE.ordinal),
        )

        insertPurchaseData(purchase)
    }

    private fun insertPurchaseData(purchase: Purchase){
        purchaseInteractor.insert(purchase)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    closeEvent.value = Unit
                    updateProgress(false)
                },
                { it }
            )
    }

    private fun checkPrice(price: String): Double {
        return if (price.isEmpty() || price.isBlank())
            PRICE_DEFAULT_DOUBLE
        else
            price.toDouble()
    }
}
