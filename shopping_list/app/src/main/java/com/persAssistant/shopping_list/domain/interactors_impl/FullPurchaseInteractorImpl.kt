package com.persAssistant.shopping_list.domain.interactors_impl

import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*
import javax.inject.Inject


class FullPurchaseInteractorImpl @Inject constructor(
    private val purchaseInteractorInterface: PurchaseInteractor,
    private val categoryInteractorInterface: CategoryInteractor
) : FullPurchaseInteractor {

    override fun getById(id: Long): Maybe<FullPurchase> {
        return purchaseInteractorInterface.getById(id)
            .flatMap { purchase ->
                convertToFullPurchase(purchase)
            }
    }

    override fun getAllByListId(id: Long): Single<LinkedList<FullPurchase>> {
        return convertToFullPurchasesList(purchaseInteractorInterface.getAllByListId(id))
    }

    override fun getAllByCategoryId(id: Long): Single<LinkedList<FullPurchase>> {
        return convertToFullPurchasesList(purchaseInteractorInterface.getAllByCategoryId(id))
    }

    override fun getAllCategory(id: Long): Single<ArrayList<String>> {
        TODO("Not yet implemented")
    }

    private fun convertToFullPurchase(purchase: Purchase): Maybe<FullPurchase> {
        return categoryInteractorInterface
            .getById(purchase.categoryId)
            .map { FullPurchase(purchase, it) }
    }

    private fun convertToFullPurchasesList(single: Single<LinkedList<Purchase>>): Single<LinkedList<FullPurchase>> {
        return single
            .toObservable()
            .flatMapIterable { it }
            .flatMap { purchase ->
                convertToFullPurchase(purchase)
                    .toObservable()
            }
            .toList()
            .map {
                val linkedList = LinkedList<FullPurchase>()
                linkedList.addAll(it)
                linkedList
            }
    }
}
