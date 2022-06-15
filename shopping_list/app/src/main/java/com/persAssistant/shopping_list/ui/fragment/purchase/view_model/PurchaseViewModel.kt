package com.persAssistant.shopping_list.ui.fragment.purchase.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.data.database.DbStruct
import com.persAssistant.shopping_list.domain.entities.Category
import javax.inject.Inject

open class PurchaseViewModel @Inject constructor() : AppBaseViewModel() {

    //TODO убрать !!
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()
    var price = MutableLiveData<String>()
    var categoryId: Long = DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
    var listId: Long = DbStruct.ShoppingListTable.Cols.INVALID_ID
    var categoryName = MutableLiveData<String>()
    var quantity = MutableLiveData<String>()
    var unit = MutableLiveData<String>()

    fun setCategory(category: Category) {
        categoryId = category.id!!
        categoryName.value = category.name
    }

    fun setPriceDefault() {
        price.value = PRICE_DEFAULT
    }

    open fun save() {}

    companion object {
        const val PRICE_DEFAULT = "0"
    }
}
