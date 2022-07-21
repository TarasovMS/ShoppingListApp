package com.persAssistant.shopping_list.ui.fragment.purchase.view_model

import android.util.Log
import com.persAssistant.shopping_list.data.database.DbStruct
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.util.ERROR
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditorPurchaseViewModel @Inject constructor(
    private val purchaseInteractor: PurchaseInteractorImpl,
    override val fullPurchaseInteractor: FullPurchaseInteractor,
) : PurchaseViewModel(fullPurchaseInteractor) {

    //TODO доделать isCompleted
    private var purchaseId: Long = 0

    fun init(id: Long) {
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

    override fun save() {
        if (listId.value != DbStruct.ShoppingListTable.Cols.INVALID_ID) {
            if (price.value.isNullOrEmpty()) setPriceDefault()

            categoryId.value?.let { categoryId ->
                listId.value?.let { listId ->
                    isCompleted.value?.let { isCompleted ->
                        val purchase = Purchase(
                            id = purchaseId,
                            name = name.value.orEmpty(),
                            categoryId = categoryId,
                            listId = listId,
                            price = price.value?.toDouble(),
                            unit = unit.value,
                            quantity = quantity.value,
                            isCompleted = isCompleted,
                        )

                        purchaseInteractor.update(purchase)
                            .subscribeOn(Schedulers.single())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                { closeEvent.value = Unit },
                                {}
                            )

                        return
                    }
                }
            }

            // если отображается эта ошибка значит пустой какое то поле
            Log.e(ERROR, " error in save EditorPurchaseViewModel")
        }
    }
}
