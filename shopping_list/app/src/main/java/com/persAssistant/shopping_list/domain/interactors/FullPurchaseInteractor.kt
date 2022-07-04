package com.persAssistant.shopping_list.domain.interactors

import com.persAssistant.shopping_list.domain.entities.FullPurchase
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

//TODO interface??

abstract class FullPurchaseInteractor {
    abstract fun getById(id: Long): Maybe<FullPurchase>
    abstract fun getAllByListId(id: Long): Single<LinkedList<FullPurchase>>
    abstract fun getAllByCategoryId(id: Long): Single<LinkedList<FullPurchase>>
    abstract fun getAllCategory(id: Long): Single<ArrayList<String>>
}
