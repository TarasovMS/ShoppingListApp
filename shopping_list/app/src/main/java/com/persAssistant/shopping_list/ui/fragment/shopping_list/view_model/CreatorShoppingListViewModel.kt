package com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model

import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.domain.interactors.ShoppingListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatorShoppingListViewModel @Inject constructor(
    private val shoppingListInteractor: ShoppingListInteractor,
) : ShoppingListViewModel() {

    //TODO add comonDispossable...
    override fun save() {
        val shoppingList = ShoppingList(name = name.value.orEmpty(), date = date)

        shoppingListInteractor.insert(shoppingList)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { closeEvent.value = Unit },
                {}
            )
    }
}
