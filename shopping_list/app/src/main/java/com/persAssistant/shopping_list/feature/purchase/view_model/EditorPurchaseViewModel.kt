package com.persAssistant.shopping_list.feature.purchase.view_model

import com.persAssistant.shopping_list.common.DEFAULT_LONG
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.common.PRICE_DEFAULT_DOUBLE
import com.persAssistant.shopping_list.common.ZERO_POSITION
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

    override fun onClickButtonSavePurchase() {
        if (listId.value != DEFAULT_INVALID_ID) {
            if (price.value.isNullOrEmpty())
                setPriceDefault()

            saveData()
        }
    }

    private fun saveData() {
        val purchase = Purchase(
            id = purchaseId,
            name = name.value.orEmpty(),
            categoryId = categoryId.value.getOrSet(DEFAULT_CATEGORIES_COUNT),
            listId = listId.value.getOrSet(DEFAULT_INVALID_ID),
            price = price.value?.toDouble().getOrSet(PRICE_DEFAULT_DOUBLE),
            unit = unit.value.orEmpty(),
            quantity = quantity.value.orEmpty(),
            isCompleted = isCompleted.value.getOrSet(ZERO_POSITION),
        )

        purchaseInteractor.update(purchase)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { closeEvent.value = Unit },
                {}
            )
    }
}
