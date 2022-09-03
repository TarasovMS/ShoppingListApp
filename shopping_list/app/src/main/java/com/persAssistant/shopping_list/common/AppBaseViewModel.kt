package com.persAssistant.shopping_list.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.common.AppBaseViewModel.ProgressState.FINISHED
import com.persAssistant.shopping_list.error.Failure

open class AppBaseViewModel : ViewModel() {

    var failureData: MutableLiveData<Failure> = MutableLiveData()
    var progressEvent: MutableLiveData<ProgressState> = MutableLiveData(FINISHED)

    enum class ProgressState {
        LOADING,
        FINISHED;

        fun isLoading() = this == LOADING
        fun isFinished() = this == FINISHED
    }

    enum class IsCompletedState(state: Int) {
        COMPLETED(1),
        ACTIVE(0);

        fun isActive() = this == ACTIVE
        fun isComplete() = this == COMPLETED
    }

    fun handleFailure(failure: Failure) {
        failureData.postValue(failure)
        updateProgressEvent(FINISHED)
        Log.e("Failure", failure.toString())
    }

    fun updateProgressEvent(state: ProgressState) {
        progressEvent.postValue(state)
    }
}
