package com.persAssistant.shopping_list.feature.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.persAssistant.shopping_list.base.AppBaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : AppBaseViewModel() {

    val initDataReceived: MutableLiveData<Boolean> = MutableLiveData()

    fun getInitData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            delay(2000)
            postData()
        }
    }

    private fun postData() {
        initDataReceived.postValue(true)
    }
}
