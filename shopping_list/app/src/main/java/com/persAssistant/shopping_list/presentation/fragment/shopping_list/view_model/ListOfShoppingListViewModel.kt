package com.persAssistant.shopping_list.presentation.fragment.shopping_list.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.domain.interactor_interfaces.ShoppingListInteractorInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ListOfShoppingListViewModel @Inject constructor(
    private val shoppingListInteractor: ShoppingListInteractorInterface
) : AppBaseViewModel() {

    var listOfShoppingList = MutableLiveData<LinkedList<ShoppingList>>()
    var deleteShoppingListId = MutableLiveData<Long>()

    fun init(lifecycleOwner: LifecycleOwner) {
        shoppingListInteractor.getChangeSignal().observe(lifecycleOwner) { initShoppingList() }
        initShoppingList()
    }

    private fun initShoppingList() {
        shoppingListInteractor.getAll()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {listOfShoppingList.value = it },
                {/*Ошибка*/ }
            )
    }

    fun deleteItemShoppingList(shoppingList: ShoppingList) {
        shoppingListInteractor.delete(shoppingList)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {deleteShoppingListId.value = shoppingList.id!! },
                {/*Ошибка*/ }
            )
    }
}