package com.persAssistant.shopping_list.feature.purchase.view_model

import com.persAssistant.shopping_list.common.AppBaseViewModel.IsCompletedState.ACTIVE
import com.persAssistant.shopping_list.common.DEFAULT_LONG
import com.persAssistant.shopping_list.common.EMPTY_STRING
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.common.PRICE_DEFAULT_DOUBLE
import com.persAssistant.shopping_list.common.QUANTITY_DEFAULT_ONE_STRING
import com.persAssistant.shopping_list.util.getOrSet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditorPurchaseViewModel @Inject constructor(
    private val purchaseInteractor: PurchaseInteractorImpl,
    override val fullPurchaseInteractor: FullPurchaseInteractor,
) : PurchaseViewModel(fullPurchaseInteractor) {

    //TODO доделать isCompleted
    private var purchaseId: Long = DEFAULT_LONG

    fun initPurchase(id: Long) {
        purchaseId = id
        getFullPurchaseById(purchaseId)
    }

    override fun onClickButtonSavePurchase() {
        if (listId.value != DEFAULT_INVALID_ID)
            saveData()
    }

    private fun getFullPurchaseById(purchaseId: Long) {
        fullPurchaseInteractor.getById(purchaseId)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    name.value = it.purchase.name
                    price.value = it.purchase.price.toString()
                    categoryId.value = it.purchase.categoryId
                    listId.value = it.purchase.listId
                    unit.value = it.purchase.unit
                    selectedCategory.value = it.category
                    quantity.value = it.purchase.quantity
                    isCompleted.value = it.purchase.isCompleted
                },
                {}
            )
    }

    private fun saveData() {
        val purchase = Purchase(
            id = purchaseId,
            name = name.value.orEmpty(),
            categoryId = categoryId.value.getOrSet(DEFAULT_CATEGORIES_COUNT),
            listId = listId.value.getOrSet(DEFAULT_INVALID_ID),
            price = checkPrice(price.value.getOrSet(EMPTY_STRING)),
            unit = unit.value.orEmpty(),
            quantity = quantity.value.getOrSet(QUANTITY_DEFAULT_ONE_STRING),
            isCompleted = isCompleted.value.getOrSet(ACTIVE.ordinal),
        )

        updatePurchaseData(purchase)
    }

    private fun updatePurchaseData(purchase: Purchase){
        purchaseInteractor.update(purchase)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    closeEvent.value = Unit
                    updateProgressEvent(ProgressState.FINISHED)
                },
                { }
            )
    }

    private fun checkPrice(price: String): Double {
        return if (price.isEmpty() || price.isBlank())
            PRICE_DEFAULT_DOUBLE
        else
            price.toDouble()
    }
}
