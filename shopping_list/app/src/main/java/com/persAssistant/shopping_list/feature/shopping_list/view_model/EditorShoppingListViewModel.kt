package com.persAssistant.shopping_list.feature.shopping_list.view_model

import com.persAssistant.shopping_list.common.DEFAULT_LONG
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.domain.interactors.ShoppingListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditorShoppingListViewModel(
    private val shoppingListInteractor: ShoppingListInteractor
) : ShoppingListViewModel() {

    private var shoppingListId: Long = DEFAULT_LONG

    override fun save() {
        val shoppingList = ShoppingList(
            id = shoppingListId,
            name = name.value.orEmpty(),
            date = date,
        )

        updateShoppingList(shoppingList)
    }

    fun init(id: Long) {
        shoppingListId = id
        getByIdShoppingList(id)
    }

    private fun getByIdShoppingList(id: Long) {
        shoppingListInteractor.getById(id)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    name.value = it.name
                    date = it.date
                },
                {}
            )
    }

    private fun updateShoppingList(shoppingList: ShoppingList) {
        shoppingListInteractor.update(shoppingList)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { closeEvent.value = Unit },
                {}
            )
    }
}
