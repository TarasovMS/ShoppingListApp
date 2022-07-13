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

    // TODO передавать значения при инициализаци в quantity и unit
    // разобраться как сделать по красоте в save, убрав 3 let
    fun init(shoppingListId: Long) {
        categoryId.value?.let { initCategoryName(it) }
        listId.value = shoppingListId
        quantity.value = QUANTITY_DEFAULT_ONE_STRING
    }

    private fun initCategoryName(categoryId: Long) {
        categoryInteractor.getById(categoryId)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categoryName.value = it.name },
                {}
            )
    }

    override fun save() {
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
                        unit = unit.value,
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
