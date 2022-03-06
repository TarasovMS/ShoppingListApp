package com.persAssistant.shopping_list.feature.billing.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.persAssistant.shopping_list.base.AppBaseViewModel
import kotlinx.coroutines.launch

class BillingViewModel(getPurchasesUseCase: GetPurchasesUseCase) : AppBaseViewModel() {

    private val _purchasedSubscriptionsLiveData = MutableLiveData<List<Purchase>?>()
    val purchasedSubscriptionsLiveData: LiveData<List<Purchase>?> = _purchasedSubscriptionsLiveData

    init {
        viewModelScope.launch {
            _purchasedSubscriptionsLiveData.value = getPurchasesUseCase.getPurchasedSubscriptions()
        }
    }

}