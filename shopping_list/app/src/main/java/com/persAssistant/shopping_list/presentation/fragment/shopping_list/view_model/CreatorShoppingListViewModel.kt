package com.persAssistant.shopping_list.presentation.fragment.shopping_list.view_model

import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.domain.interactor_interfaces.ShoppingListInteractorInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatorShoppingListViewModel @Inject constructor(private val shoppingListInteractor: ShoppingListInteractorInterface) :
    ShoppingListViewModel() {

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