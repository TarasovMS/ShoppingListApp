package com.persAssistant.shopping_list.domain.interactors

import androidx.lifecycle.LiveData
import com.persAssistant.shopping_list.data.dao.entity.RoomShoppingList
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

interface ShoppingListInteractor {
    fun getChangeSignal(): LiveData<List<RoomShoppingList>>
    fun insert(shoppingList: ShoppingList): Completable
    fun getAll(): Single<LinkedList<ShoppingList>>
    fun getById(id: Long): Maybe<ShoppingList>
    fun update(shoppingList: ShoppingList): Completable
    fun delete(shoppingList: ShoppingList): Completable
}
