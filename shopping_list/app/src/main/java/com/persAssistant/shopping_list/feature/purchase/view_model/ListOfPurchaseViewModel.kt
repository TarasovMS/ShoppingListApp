package com.persAssistant.shopping_list.feature.purchase.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.common.DEFAULT_LONG
import com.persAssistant.shopping_list.common.EMPTY_STRING
import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ListOfPurchaseViewModel @Inject constructor(
    val purchaseInteractor: PurchaseInteractor,
    val fullPurchaseInteractor: FullPurchaseInteractor,
) : AppBaseViewModel() {

    var fullPurchaseList = MutableLiveData<LinkedList<FullPurchase>>()
    var deletePurchaseId = MutableLiveData(DEFAULT_LONG)
    var name = MutableLiveData(EMPTY_STRING)

    enum class IdTypes {
        CATEGORY,
        SHOPPINGLIST;
    }

    fun init(
        lifecycleOwner: LifecycleOwner,
        parentId: Long,
        type: IdTypes,
    ) {
        initByIdType(parentId, type)

        purchaseInteractor.getChangeSignal().observe(lifecycleOwner) {
            initByIdType(parentId, type)
        }
    }

    private fun initByIdType(parentId: Long, type: IdTypes) {
        if (type == IdTypes.SHOPPINGLIST)
            initByListId(parentId)
        else
            initByCategoryId(parentId)
    }

    fun deleteItemPurchase(purchase: Purchase) {
        purchaseInteractor.delete(purchase)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {/*Выполнено*/ },
                {/*Ошибка*/ }
            )
    }

    private fun initByCategoryId(id: Long) {
        fullPurchaseInteractor.getAllByCategoryId(id)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { fullPurchaseList.value = it },
                {/*Ошибка*/ }
            )
    }

    private fun initByListId(id: Long) {
        fullPurchaseInteractor.getAllByListId(id)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { fullPurchaseList.value = it },
                {/*Ошибка*/ }
            )
    }
}
