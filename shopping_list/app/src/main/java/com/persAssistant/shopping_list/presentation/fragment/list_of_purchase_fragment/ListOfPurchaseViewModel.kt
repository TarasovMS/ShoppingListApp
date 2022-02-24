package com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactor_interfaces.FullPurchaseInteractorInterface
import com.persAssistant.shopping_list.domain.interactor_interfaces.PurchaseInteractorInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class ListOfPurchaseViewModel @Inject constructor(val purchaseInteractor: PurchaseInteractorInterface, val fullPurchaseInteractor: FullPurchaseInteractorInterface
    ): AppBaseViewModel() {


    var fullPurchaseList = MutableLiveData<LinkedList<FullPurchase>>()
    var deletePurchaseId = MutableLiveData<Long>()
    var name = MutableLiveData<String>()

    enum class IdTypes {
        CATEGORY,
        SHOPPINGLIST;
    }

    fun init(lifecycleOwner: LifecycleOwner, parentId: Long, type: IdTypes){
        initByIdType(parentId, type)

        purchaseInteractor.getChangeSignal().observe(lifecycleOwner, androidx.lifecycle.Observer {
            initByIdType(parentId, type)
        })
    }

    private fun initByIdType(parentId: Long, type: IdTypes){
        if (type == IdTypes.SHOPPINGLIST)
            initByListId(parentId)
        else
            initByCategoryId(parentId)
    }

    fun deleteItemPurchase(purchase: Purchase){
        purchaseInteractor.delete(purchase)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({/*Выполнено*/ }, {/*Ошибка*/ })
    }

    private fun initByCategoryId(id: Long) {
        fullPurchaseInteractor.getAllByCategoryId(id)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                /*Есть данные*/
                fullPurchaseList.value = it
            }, {/*Ошибка*/ })
    }

    private fun initByListId(id: Long) {
        fullPurchaseInteractor.getAllByListId(id)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                /*Есть данные*/
                fullPurchaseList.value = it
            }, {/*Ошибка*/ })
    }
}