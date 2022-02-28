package com.persAssistant.shopping_list.presentation.activity.category

import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.interactor_interfaces.CategoryInteractorInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatorCategoryViewModel @Inject constructor(val categoryInteractor: CategoryInteractorInterface)
    : CategoryViewModel() {

    override fun save() {
        val category = Category(name = name.value ?: "")
        categoryInteractor.insert(category)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                closeEvent.value = Unit
            }, {})
    }
}