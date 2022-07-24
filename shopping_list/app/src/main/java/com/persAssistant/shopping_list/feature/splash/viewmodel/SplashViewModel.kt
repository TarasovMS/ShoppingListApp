package com.persAssistant.shopping_list.feature.splash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.persAssistant.shopping_list.common.AppBaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : AppBaseViewModel() {

    val initDataReceived: MutableLiveData<Boolean> = MutableLiveData()

    fun getInitData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            delay(TIME_MILLIS)
            postData()
        }
    }

    private fun postData() {
        initDataReceived.postValue(true)
    }

    companion object {
        private const val TIME_MILLIS = 2500L
    }
}
