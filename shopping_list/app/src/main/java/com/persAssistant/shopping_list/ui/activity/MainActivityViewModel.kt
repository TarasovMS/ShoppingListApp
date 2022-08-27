package com.persAssistant.shopping_list.ui.activity

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : AppBaseViewModel() {
    val networkStateLiveData = MutableLiveData<Boolean>()
}
