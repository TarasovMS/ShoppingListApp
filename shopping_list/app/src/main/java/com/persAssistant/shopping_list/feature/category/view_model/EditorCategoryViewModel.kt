package com.persAssistant.shopping_list.feature.category.view_model

import com.persAssistant.shopping_list.common.DEFAULT_LONG
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditorCategoryViewModel @Inject constructor(
    val categoryInteractor: CategoryInteractor,
) : CategoryViewModel() {

    private var categoryId: Long = DEFAULT_LONG

    fun init(id: Long) {
        categoryId = id

        categoryInteractor.getById(id)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { name.value = it.name },
                {}
            )
    }

    override fun save() {
        val category = Category(id = categoryId, name = name.value.orEmpty())

        categoryInteractor.update(category)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { closeEvent.value = Unit },
                {}
            )
    }
}
