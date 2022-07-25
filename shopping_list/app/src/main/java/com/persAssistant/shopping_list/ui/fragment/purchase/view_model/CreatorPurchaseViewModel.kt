package com.persAssistant.shopping_list.ui.fragment.purchase.view_model

import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.domain.interactors_impl.PurchaseInteractorImpl
import com.persAssistant.shopping_list.util.QUANTITY_DEFAULT_ONE_STRING
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatorPurchaseViewModel @Inject constructor(
    val purchaseInteractor: PurchaseInteractorImpl,
    val categoryInteractor: CategoryInteractorImpl,
    override val fullPurchaseInteractor: FullPurchaseInteractor,
) : PurchaseViewModel(fullPurchaseInteractor) {

    //TODO разобраться как сделать по красоте в save, убрав 3 let
    fun init(shoppingListId: Long) {
        listId.value = shoppingListId
        quantity.value = QUANTITY_DEFAULT_ONE_STRING
    }

    override fun save() {
        super.save()

        if (price.value.isNullOrEmpty()) setPriceDefault()

        // не нравится эта провеерка внутри проверки
        categoryId.value?.let { categoryId ->
            listId.value?.let { listId ->
                isCompleted.value?.let { isCompleted ->
                    val purchase = Purchase(
                        name = name.value.orEmpty(),
                        categoryId = categoryId,
                        listId = listId,
                        price = price.value?.toDouble(),
                        unit = unit.value.orEmpty(),
                        quantity = quantity.value,
                        isCompleted = isCompleted,
                    )

                    purchaseInteractor.insert(purchase)
                        .subscribeOn(Schedulers.single())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { closeEvent.value = Unit },
                            {}
                        )
                }
            }
        }
    }
}
