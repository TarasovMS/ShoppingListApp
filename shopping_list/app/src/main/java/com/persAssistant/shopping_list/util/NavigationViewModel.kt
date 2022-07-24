package com.persAssistant.shopping_list.util

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.persAssistant.shopping_list.common.AppBaseViewModel
import javax.inject.Inject

open class NavigationViewModel @Inject constructor() : AppBaseViewModel() {
    val directionData: MutableLiveData<NavDirections> = MutableLiveData()
    val idData: MutableLiveData<Int> = MutableLiveData()
}
