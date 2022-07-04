package com.persAssistant.shopping_list.ui.fragment.purchase.view_model

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.base.IsCompletedState
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.INVALID_ID
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.util.PRICE_DEFAULT
import javax.inject.Inject

open class PurchaseViewModel @Inject constructor() : AppBaseViewModel() {

    //TODO протестировать работу isCompleted
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()
    var price = MutableLiveData<String>()
    var categoryId = MutableLiveData(DEFAULT_CATEGORIES_COUNT)
    var listId = MutableLiveData(INVALID_ID)
    var categoryName = MutableLiveData<String>()
    var quantity = MutableLiveData<String>()
    var unit = MutableLiveData<String>()
    var isCompleted = MutableLiveData(IsCompletedState.ACTIVE.ordinal)
    var categoriesNames = MutableLiveData<Array<String>>()

    open fun save() {}

    fun setCategory(category: Category) {
        categoryId.value = category.id ?: DEFAULT_CATEGORIES_COUNT
        categoryName.value = category.name
    }

    fun setPriceDefault() {
        price.value = PRICE_DEFAULT
    }

    fun getCategoriesNames(){
//        fullpurchaseInteractor.getAllCategories()
//            .subscribeOn(Schedulers.single())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { },
//                { }
//            )
    }

}
