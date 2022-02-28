package com.persAssistant.shopping_list.presentation.fragment.purchase.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.data.database.DbStruct
import com.persAssistant.shopping_list.domain.entities.Category
import javax.inject.Inject

open class PurchaseViewModel @Inject constructor(): ViewModel() {
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()
    var price = MutableLiveData<String>()
    var categoryId: Long = DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
    var listId: Long = DbStruct.ShoppingListTable.Cols.INVALID_ID
    var categoryName = MutableLiveData<String>()

    fun setCategory(category: Category){
        categoryId = category.id!!
        categoryName.value = category.name
    }

    fun setPriceDefault() { price.value = "0" }

    open fun save() {}
}