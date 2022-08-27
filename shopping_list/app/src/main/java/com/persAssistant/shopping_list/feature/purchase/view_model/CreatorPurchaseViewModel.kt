package com.persAssistant.shopping_list.feature.purchase.view_model

import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.common.PRICE_DEFAULT_DOUBLE
import com.persAssistant.shopping_list.common.QUANTITY_DEFAULT_ONE_STRING
import com.persAssistant.shopping_list.common.ZERO_POSITION
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
        quantity.value = QUANTITY_DEFAULT_ONE_STRING
    }

    override fun onClickButtonSavePurchase() {
        if (price.value.isNullOrEmpty()) setPriceDefault()
        saveData()
    }

    private fun saveData() {
        val purchase = Purchase(
            name = name.value.orEmpty(),
            categoryId = categoryId.value.getOrSet(DEFAULT_CATEGORIES_COUNT),
            listId = listId.value.getOrSet(DEFAULT_INVALID_ID),
            price = price.value?.toDouble().getOrSet(PRICE_DEFAULT_DOUBLE),
            unit = unit.value.orEmpty(),
            quantity = quantity.value.orEmpty(),
            isCompleted = isCompleted.value.getOrSet(ZERO_POSITION),
        )

        purchaseInteractor.insert(purchase)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    closeEvent.value = Unit
                    updateProgress(false)
                },
                {
                    it
                }
            )
    }
}
