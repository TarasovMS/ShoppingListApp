package com.persAssistant.shopping_list.feature.category.view_model

import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatorCategoryViewModel @Inject constructor(
    val categoryInteractor: CategoryInteractor,
) : CategoryViewModel() {

    //TODO create validationName
    override fun save() {
        val category = Category(name = name.value.orEmpty())

        categoryInteractor.insert(category)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { closeEvent.value = Unit },
                {}
            )
    }
}
