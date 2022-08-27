package com.persAssistant.shopping_list.feature.purchase.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.common.AppBaseViewModel.IsCompletedState.ACTIVE
import com.persAssistant.shopping_list.common.EMPTY_STRING
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.domain.interactors.FullPurchaseInteractor
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.feature.purchase.view_model.PurchaseViewModel.FieldPurchaseValidation.NAME
import com.persAssistant.shopping_list.common.PRICE_DEFAULT_STRING
import com.persAssistant.shopping_list.common.QUANTITY_DEFAULT_ONE_STRING
import com.persAssistant.shopping_list.error.RegistrationError
import com.persAssistant.shopping_list.util.getOrSet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class PurchaseViewModel @Inject constructor(
    open val fullPurchaseInteractor: FullPurchaseInteractor
) : AppBaseViewModel() {

    //TODO протестировать работу isCompleted
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData(EMPTY_STRING)
    var price = MutableLiveData(PRICE_DEFAULT_STRING)
    var categoryId = MutableLiveData(DEFAULT_CATEGORIES_COUNT)
    var listId = MutableLiveData(DEFAULT_INVALID_ID)
    var selectedCategory = MutableLiveData<Category>()
    var quantity = MutableLiveData(QUANTITY_DEFAULT_ONE_STRING)
    var unit = MutableLiveData(EMPTY_STRING)
    var isCompleted = MutableLiveData(ACTIVE.ordinal)
    var allCategories = MutableLiveData<ArrayList<Category>>()

    val errorValidation: MutableLiveData<Failure> = MutableLiveData()

    open fun onClickButtonSavePurchase() {}

    fun setCategory(category: Category) {
        categoryId.value = category.id.getOrSet(DEFAULT_CATEGORIES_COUNT)
        selectedCategory.value = category
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

    private fun validateName(nameValue: String) {
        fullPurchaseInteractor.validateName(nameValue).either(
            functionError = { failure ->
                handleFailure(failure)
                errorValidation.postValue(RegistrationError.NameValidationError)
            },

            functionSuccess = {
                onClickButtonSavePurchase()
                Log.d("validateNameSuccess", it)
            }
        )
    }

    fun validateInputs(
        field: FieldPurchaseValidation,
        name: String,
    ) {
        when (field) {
            NAME -> validateName(nameValue = name)
        }
    }

    enum class FieldPurchaseValidation {
        NAME,
    }

}
