package com.persAssistant.shopping_list.domain.interactors_impl

import androidx.lifecycle.LiveData
import com.persAssistant.shopping_list.data.dao.entity.RoomPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import com.persAssistant.shopping_list.domain.interactor_repositories.PurchaseRepo
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

class PurchaseInteractorImpl(
    private val purchaseRepositoryInterface: PurchaseRepo
) : PurchaseInteractor {

    override fun getChangeSignal(): LiveData<List<RoomPurchase>> {
        return purchaseRepositoryInterface.getChangeSignal()
    }

    override fun insert(purchase: Purchase): Completable {
        return purchaseRepositoryInterface.insert(purchase)
    }

    override fun getAll(): Single<LinkedList<Purchase>> {
        return purchaseRepositoryInterface.getAll()
    }

    override fun getById(id: Long): Maybe<Purchase> {
        return purchaseRepositoryInterface.getById(id)
    }

    override fun getAllByListId(id: Long): Single<LinkedList<Purchase>> {
        return purchaseRepositoryInterface.getAllByListId(id)
    }

    override fun getAllByCategoryId(id: Long): Single<LinkedList<Purchase>> {
        return purchaseRepositoryInterface.getAllByCategoryId(id)
    }

    override fun update(purchase: Purchase): Completable {
        return purchaseRepositoryInterface.update(purchase)
    }

    override fun delete(purchase: Purchase): Completable {
        return purchaseRepositoryInterface.delete(purchase)
    }
}
