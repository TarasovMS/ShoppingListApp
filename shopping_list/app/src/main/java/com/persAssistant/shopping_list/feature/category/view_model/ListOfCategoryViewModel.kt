package com.persAssistant.shopping_list.feature.category.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ListOfCategoryViewModel @Inject constructor(
    val categoryInteractor: CategoryInteractor,
) : AppBaseViewModel() {

    var categoryList = MutableLiveData<LinkedList<Category>>()

    fun init(lifecycleOwner: LifecycleOwner) {
        categoryInteractor.getChangeSignal().observe(lifecycleOwner) {
            initCategoryList()
        }

        initCategoryList()
    }

    private fun initCategoryList() {
        categoryInteractor.getAll()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categoryList.value = it },
                { }
            )
    }

    fun deleteItemCategory(category: Category) {
        categoryInteractor.delete(category)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { },
                { }
            )
    }
}
