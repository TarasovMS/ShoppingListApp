package com.persAssistant.shopping_list.data.service

import androidx.lifecycle.LiveData
import com.persAssistant.shopping_list.data.dao.PurchaseRoomDao
import com.persAssistant.shopping_list.data.dao.entity.RoomPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.lang.Exception
import java.util.*

class PurchaseService(
    private val purchaseRoomDao: PurchaseRoomDao
) {

    //TODO получть сттроку из string

    // сигнал об изменении в таблице
    fun getChangeSignal(): LiveData<List<RoomPurchase>> {
        return purchaseRoomDao.getChangeSignal()
    }

    // добавления записи в таблицу
    fun insert(purchase: Purchase): Completable {
        return Completable.fromAction {
            val result = purchaseRoomDao.insert(convertInRoomPurchase(purchase))
            if (result == -1L) throw Exception("Failed to execute insert")
            purchase.id = result
        }
    }

    //запрос одного списка по айди
    fun getById(id: Long): Maybe<Purchase> {
        return purchaseRoomDao.getById(id)
            .map { convertInPurchase(it) }
    }

    private fun processDaoPurchases(single: Single<List<RoomPurchase>>): Single<LinkedList<Purchase>> {
        return single.toObservable()
            .flatMapIterable {/*list*/ it }
            .map { convertInPurchase(it) }
            .toList()
            .map {
                val linkedList = LinkedList<Purchase>()
                linkedList.addAll(it)
                linkedList
            }
    }

    //запрос всех списков
    fun getAll(): Single<LinkedList<Purchase>> {
        return processDaoPurchases(purchaseRoomDao.getAll())
    }

    //запрос списка покупок относящегося к определенной покупке по айди
    fun getAllByListId(id: Long): Single<LinkedList<Purchase>> {
        return processDaoPurchases(purchaseRoomDao.getAllByListId(id))
    }

    //запрос категорий относящихся к определенной покупке по айди
    fun getAllByCategoryId(id: Long): Single<LinkedList<Purchase>> {
        return processDaoPurchases(purchaseRoomDao.getAllByCategoryId(id))
    }

    //обновление списка
    fun update(purchase: Purchase): Completable {
        return Completable.fromAction {
            purchaseRoomDao.update(convertInRoomPurchase(purchase))
        }
    }

    //удаление списка
    fun delete(purchase: Purchase): Completable {
        return Completable.fromAction {
            purchaseRoomDao.delete(convertInRoomPurchase(purchase))
        }
    }

    private fun convertInPurchase(value: RoomPurchase): Purchase {
        return Purchase(
            id = value.id,
            name = value.name,
            categoryId = value.categoryId,
            listId = value.listId,
            price = value.price,
            quantity = value.quantity,
            unit = value.unit,
            isCompleted = value.isCompleted,
        )
    }

    private fun convertInRoomPurchase(value: Purchase): RoomPurchase {
        return RoomPurchase(
            id = value.id,
            name = value.name,
            categoryId = value.categoryId,
            listId = value.listId,
            price = value.price,
            quantity = value.quantity,
            unit = value.unit,
            isCompleted = value.isCompleted,
        )
    }
}
