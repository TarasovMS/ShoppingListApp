package com.persAssistant.shopping_list.presentation.fragment.purchase.view_model

import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatorPurchaseViewModel @Inject constructor(val purchaseInteractor: PurchaseInteractor,
                                                   val categoryInteractor: CategoryInteractor
                                                   ) : PurchaseViewModel() {

    fun init (shoppingListId: Long){
        initCategoryName(categoryId)
        listId = shoppingListId
    }

    private fun initCategoryName ( categoryId: Long){
        categoryInteractor.getById(categoryId)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                categoryName.value = it.name
            }, {})
    }

    override fun save() {
        if(price.value == null) setPriceDefault()

        val purchase = Purchase(
            name = name.value ?: "",
            categoryId = categoryId,
            listId = listId,
            price = price.value?.toDouble(),
            isCompleted = 0
        )
        purchaseInteractor.insert(purchase)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                closeEvent.value = Unit
            }, {})
    }
}