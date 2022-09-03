package com.persAssistant.shopping_list.feature.shopping_list.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.domain.interactors.ShoppingListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ListOfShoppingListViewModel @Inject constructor(
    private val shoppingListInteractor: ShoppingListInteractor,
) : AppBaseViewModel() {

    var listOfShoppingList = MutableLiveData<LinkedList<ShoppingList>>()

    fun init(lifecycleOwner: LifecycleOwner) {
        shoppingListInteractor.getChangeSignal().observe(lifecycleOwner) { initShoppingList() }
        initShoppingList()
    }

    private fun initShoppingList() {
        shoppingListInteractor.getAll()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { listOfShoppingList.value = it },
                {/*Ошибка*/ }
            )
    }

    fun deleteItemShoppingList(shoppingList: ShoppingList) {
        shoppingListInteractor.delete(shoppingList)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { },
                { }
            )
    }

}
