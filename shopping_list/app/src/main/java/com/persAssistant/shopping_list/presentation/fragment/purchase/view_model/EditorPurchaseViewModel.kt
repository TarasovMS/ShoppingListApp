package com.persAssistant.shopping_list.presentation.fragment.purchase.view_model

import com.persAssistant.shopping_list.data.database.DbStruct
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactor_interfaces.FullPurchaseInteractorInterface
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditorPurchaseViewModel @Inject constructor(
    private val purchaseInteractor: PurchaseInteractor,
    private val fullPurchaseInteractor: FullPurchaseInteractorInterface
) : PurchaseViewModel() {

    private var purchaseId: Long = 0

    fun init(id: Long) {
        purchaseId = id

        fullPurchaseInteractor.getById(purchaseId)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categoryName.value = it.category.name
                    name.value = it.purchase.name
                    price.value = it.purchase.price.toString()
                    categoryId = it.purchase.categoryId
                    listId = it.purchase.listId
                },
                {}
            )
    }

    override fun save() {

        if (listId != DbStruct.ShoppingListTable.Cols.INVALID_ID) {

            if (price.value == null) setPriceDefault()

            val purchase = Purchase(
                id = purchaseId,
                name = name.value.orEmpty(),
                categoryId = categoryId,
                listId = listId,
                price = price.value?.toDouble(),
                isCompleted = 0
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
}