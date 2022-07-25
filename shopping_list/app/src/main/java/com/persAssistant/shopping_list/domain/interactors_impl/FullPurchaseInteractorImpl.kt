package com.persAssistant.shopping_list.domain.interactors_impl

import com.persAssistant.shopping_list.data.handler.ValidateHandler
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.domain.interactors.PurchaseInteractor
import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FullPurchaseInteractorImpl @Inject constructor(
    private val purchaseInteractorInterface: PurchaseInteractor,
    private val categoryInteractorInterface: CategoryInteractor,
    private val validateHandler: ValidateHandler,
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

    override fun getAllCategories(): Single<ArrayList<Category>> {
        return categoryInteractorInterface.getAll()
            .toObservable()
            .flatMapIterable { it }
            .map { it }
            .toList()
            .map {
                val linkedList = ArrayList<Category>()
                linkedList.addAll(it)
                linkedList
            }
    }

    override fun validationName(name: String): ExecutionResult<Failure, String> {
        return validateHandler.validationFields(name)
    }

    private fun convertToFullPurchasesList(single: Single<LinkedList<Purchase>>): Single<LinkedList<FullPurchase>> {
        return single
            .toObservable()
            .flatMapIterable { it }
            .flatMap { purchase ->
                convertToFullPurchase(purchase).toObservable()
            }
            .toList()
            .map {
                val linkedList = LinkedList<FullPurchase>()
                linkedList.addAll(it)
                linkedList
            }
    }

    private fun convertToFullPurchase(purchase: Purchase): Maybe<FullPurchase> {
        return categoryInteractorInterface
            .getById(purchase.categoryId)
            .map { FullPurchase(purchase, it) }
    }

}
