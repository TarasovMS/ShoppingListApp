package com.persAssistant.shopping_list.feature.user_help.handling.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.base.handleFailure
import com.persAssistant.shopping_list.base.updateProgress
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.feature.user_help.handling.model.Handling
import com.persAssistant.shopping_list.feature.user_help.handling.ui.HandlingView
import com.persAssistant.shopping_list.feature.user_help.handling.usecase.HandlingUseCase
import javax.inject.Inject

class HandlingViewModel @Inject constructor(
    private val useCase: HandlingUseCase
) : AppBaseViewModel(),
//    ViewError,
    HandlingView
{

    val isActionEnabled = MutableLiveData(false)
    val handling: MutableLiveData<Handling> = MutableLiveData()
    val errorMessage: MutableLiveData<Failure> = MutableLiveData()

    fun validateData( name: String, message: String) {
        isActionEnabled.postValue(
             name.isNotBlank() && message.isNotBlank()
        )
    }

    fun saveData( name: String, message: String) {
        updateProgress(true)

        useCase.validateName(name).either(
            functionError = {
                handleFailure(it)
                errorMessage.postValue(it)
            },

            functionSuccess = {
                Log.d("успех", "успех")
            }
        )

        useCase.validateMessage(message).either(
            functionError = {
                handleFailure(it)
                errorMessage.postValue(it)
            },

            functionSuccess = {
                Log.d("успех", "успех")
            }
        )
    }

    private fun saveHandlingSuccess(data: Handling) {
        handling.postValue(data)
        updateProgress(false)
    }

//    override fun showFieldError(id: Int, fieldView: View, parentLayout: TextInputLayout?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun focusOnView(unusedMessage: String?, fieldView: View) {
//        TODO("Not yet implemented")
//    }
//
//    override fun showTextInputFieldError(input: EditText, parentLayout: TextInputLayout?) {
//        TODO("Not yet implemented")
//    }

    override fun tittleError(message: String) {
        TODO("Not yet implemented")
    }

    override fun tittleError(id: Int) {
        TODO("Not yet implemented")
    }

    override fun messageError(message: String) {
        TODO("Not yet implemented")
    }

    override fun messageError(id: Int) {
        TODO("Not yet implemented")
    }
}
