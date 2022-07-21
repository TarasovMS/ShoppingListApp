package com.persAssistant.shopping_list.domain.interactors

import androidx.lifecycle.LiveData
import com.persAssistant.shopping_list.data.dao.entity.RoomCategory
import com.persAssistant.shopping_list.domain.entities.Category
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

interface CategoryInteractor{
    fun getChangeSignal(): LiveData<List<RoomCategory>>
    fun insert(category: Category): Completable
    fun getAll(): Single<LinkedList<Category>>
    fun getById(id: Long): Maybe<Category>
    fun update(category: Category): Completable
    fun delete(category: Category): Completable
}
