package com.pers_assistant.shopping_list.feature.user_help.handling.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pers_assistant.shopping_list.base.AppBaseViewModel
import com.pers_assistant.shopping_list.base.handleFailure
import com.pers_assistant.shopping_list.error.Failure
import com.pers_assistant.shopping_list.feature.user_help.handling.model.Handling
import com.pers_assistant.shopping_list.feature.user_help.handling.usecase.HandlingUseCase
import javax.inject.Inject

class HandlingViewModel @Inject constructor(
    private val useCase: HandlingUseCase
) : AppBaseViewModel() {

    enum class FieldValidation {
        TITLE,
        MESSAGE,
        ALL_FIELDS;

        fun isTitle() = this == TITLE
        fun isMessage() = this == MESSAGE
        fun isAllFields() = this == ALL_FIELDS
    }

    val isActionEnabled = MutableLiveData(false)
    val handling: MutableLiveData<Handling> = MutableLiveData()
    val errorMessage: MutableLiveData<Failure> = MutableLiveData()
    val errorName: MutableLiveData<Failure> = MutableLiveData()

    //TODO уточнить надо ли
    fun validateData(name: String, message: String) {
        isActionEnabled.postValue(name.isNotBlank() && message.isNotBlank())
        validateTitle(name)
        validateMessage(message)
    }

    fun validateTitle(name: String) {
        useCase.validateTitle(name)
            .either(
                functionError = {
                    handleFailure(it)
                    errorName.postValue(it)
                    isActionEnabled.postValue(false)
                },

                functionSuccess = {
                    //TODO доделать
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
                    //TODO доделать
                    Log.d("validateMessage", it)
                }
            )
    }
}
