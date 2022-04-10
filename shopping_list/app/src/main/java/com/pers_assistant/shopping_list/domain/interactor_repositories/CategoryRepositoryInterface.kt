package com.pers_assistant.shopping_list.domain.interactor_repositories

import androidx.lifecycle.LiveData
import com.pers_assistant.shopping_list.data.dao.entity.RoomCategory
import com.pers_assistant.shopping_list.domain.entities.Category
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

abstract class CategoryRepositoryInterface {
    abstract fun getChangeSignal(): LiveData<List<RoomCategory>>
    abstract fun insert(category: Category): Completable
    abstract fun getAll(): Single<LinkedList<Category>>
    abstract fun getById(id: Long): Maybe<Category>
    abstract fun update(category: Category): Completable
    abstract fun delete(category: Category): Completable
}