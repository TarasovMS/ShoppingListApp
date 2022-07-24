package com.persAssistant.shopping_list.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.util.Event

open class AppBaseViewModel : ViewModel() {

    var failureData: MutableLiveData<Event<Failure>> = MutableLiveData()
    var progressData: MutableLiveData<Boolean> = MutableLiveData()
    var progressEvent: MutableLiveData<Event<ProgressState>> = MutableLiveData()

    enum class ProgressState {
        LOADING,
        FINISHED;

        fun isLoading() = this == LOADING
        fun isFinished() = this == FINISHED
    }

    enum class IsCompletedState(state: Int) {
        COMPLETED(1),
        ACTIVE(0);
    }

    fun handleFailure(failure: Failure) {
        failureData.postValue(Event(failure))
        Log.e("Failure", failure.toString())
        updateProgressEvent(ProgressState.FINISHED)
        updateProgress(false)
    }

    fun updateProgress(progress: Boolean) {
        progressData.postValue(progress)
    }

    fun updateProgressEvent(state: ProgressState) {
        progressEvent.postValue(Event(state))
    }
}
