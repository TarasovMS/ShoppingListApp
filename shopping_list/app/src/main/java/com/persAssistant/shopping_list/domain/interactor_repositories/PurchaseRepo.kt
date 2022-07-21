package com.persAssistant.shopping_list.domain.interactor_repositories

import androidx.lifecycle.LiveData
import com.persAssistant.shopping_list.data.dao.entity.RoomPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

interface PurchaseRepo {
    fun getChangeSignal(): LiveData<List<RoomPurchase>>
    fun insert(purchase: Purchase): Completable
    fun getAll(): Single<LinkedList<Purchase>>
    fun getById(id: Long): Maybe<Purchase>
    fun getAllByListId(id: Long): Single<LinkedList<Purchase>>
    fun getAllByCategoryId(id: Long): Single<LinkedList<Purchase>>
    fun update(purchase: Purchase): Completable
    fun delete(purchase: Purchase): Completable
}
