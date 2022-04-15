package com.persAssistant.shopping_list.ui.activity

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.persAssistant.shopping_list.util.Event
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(): AppBaseViewModel(){
    val networkStateLiveData = MutableLiveData<Event<Boolean>>()
}
