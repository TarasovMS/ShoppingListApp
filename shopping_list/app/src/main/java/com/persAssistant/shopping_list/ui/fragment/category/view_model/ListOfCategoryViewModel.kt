package com.persAssistant.shopping_list.ui.fragment.category.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.interactor_interfaces.CategoryInteractorInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ListOfCategoryViewModel @Inject constructor(val categoryInteractor: CategoryInteractorInterface) :
    AppBaseViewModel() {


    var categoryList = MutableLiveData<LinkedList<Category>>()
    var deleteCategoryId = MutableLiveData<Long>()

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
                { deleteCategoryId.value = category.id!! },
                { }
            )
    }
}