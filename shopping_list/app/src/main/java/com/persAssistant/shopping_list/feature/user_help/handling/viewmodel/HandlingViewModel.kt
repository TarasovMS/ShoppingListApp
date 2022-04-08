package com.persAssistant.shopping_list.feature.user_help.handling.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.base.handleFailure
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.feature.user_help.handling.model.Handling
import com.persAssistant.shopping_list.feature.user_help.handling.usecase.HandlingUseCase
import javax.inject.Inject

class HandlingViewModel @Inject constructor(
    private val useCase: HandlingUseCase
) : AppBaseViewModel() {

    enum class FieldValidation {
        NAME,
        MESSAGE,
        ALL_FIELDS;

        fun isName() = this == NAME
        fun isMessage() = this == MESSAGE
        fun isAllFields() = this == ALL_FIELDS
    }

    val isActionEnabled = MutableLiveData(false)
    val handling: MutableLiveData<Handling> = MutableLiveData()
    val errorMessage: MutableLiveData<Failure> = MutableLiveData()
    val errorName: MutableLiveData<Failure> = MutableLiveData()

    fun validateData(name: String, message: String) {
        isActionEnabled.postValue(name.isNotBlank() && message.isNotBlank())
        validateTitle(name)
        validateMessage(message)
    }

    fun validateTitle(name: String) {
        useCase.validateName(name)
            .either(
                functionError = {
                    handleFailure(it)
                    errorName.postValue(it)
                    isActionEnabled.postValue(false)
                },

                functionSuccess = {
                    Log.d("validateName", it)
                }
            )
    }

    fun validateMessage(message: String) {
        useCase.validateMessage(message)
            .either(
                functionError = {
                    handleFailure(it)
                    errorMessage.postValue(it)
                    isActionEnabled.postValue(false)
                },

                functionSuccess = {
                    Log.d("validateMessage", it)
                }
            )
    }
}
