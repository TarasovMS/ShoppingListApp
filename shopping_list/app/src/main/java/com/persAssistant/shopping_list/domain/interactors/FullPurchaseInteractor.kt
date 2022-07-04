package com.persAssistant.shopping_list.domain.interactors

import com.persAssistant.shopping_list.domain.entities.FullPurchase
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

//TODO interface??

interface FullPurchaseInteractor {
    fun getById(id: Long): Maybe<FullPurchase>
    fun getAllByListId(id: Long): Single<LinkedList<FullPurchase>>
    fun getAllByCategoryId(id: Long): Single<LinkedList<FullPurchase>>
    fun getAllCategory(id: Long): Single<ArrayList<String>>
}
