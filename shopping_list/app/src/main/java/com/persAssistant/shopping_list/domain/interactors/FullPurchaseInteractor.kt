package com.persAssistant.shopping_list.domain.interactors

import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

interface FullPurchaseInteractor {

    fun getById(id: Long): Maybe<FullPurchase>

    fun getAllByListId(id: Long): Single<LinkedList<FullPurchase>>

    fun getAllByCategoryId(id: Long): Single<LinkedList<FullPurchase>>

    fun getAllCategories(): Single<ArrayList<Category>>

    fun validateName(name: String): ExecutionResult<Failure, String>

}
