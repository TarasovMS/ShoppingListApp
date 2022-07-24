package com.persAssistant.shopping_list.ui.fragment.purchase.view_model

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.INVALID_ID
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.util.PRICE_DEFAULT_STRING
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class PurchaseViewModel @Inject constructor(
    open val fullPurchaseInteractor: FullPurchaseInteractor
) : AppBaseViewModel() {

    //TODO протестировать работу isCompleted
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()
    var price = MutableLiveData<String>()
    var categoryId = MutableLiveData(DEFAULT_CATEGORIES_COUNT)
    var listId = MutableLiveData(INVALID_ID)
    var selectedCategory = MutableLiveData<Category>()
    var quantity = MutableLiveData<String>()
    var unit = MutableLiveData<String>()
    var isCompleted = MutableLiveData(IsCompletedState.ACTIVE.ordinal)
    var allCategories = MutableLiveData<ArrayList<Category>>()

    open fun save() {}

    fun setCategory(category: Category) {
        categoryId.value = category.id ?: DEFAULT_CATEGORIES_COUNT
        selectedCategory.value = category
    }

    fun setPriceDefault() {
        price.value = PRICE_DEFAULT_STRING
    }

    fun validation(name: String) {

    }

    fun getCategoriesNames() {
        fullPurchaseInteractor.getAllCategories()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { allCategories.postValue(it) },
                { }
            )
    }

}
