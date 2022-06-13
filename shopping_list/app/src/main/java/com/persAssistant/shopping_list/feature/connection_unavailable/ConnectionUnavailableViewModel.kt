package com.persAssistant.shopping_list.feature.connection_unavailable

import com.persAssistant.shopping_list.base.AppBaseViewModel
import javax.inject.Inject

class ConnectionUnavailableViewModel @Inject constructor() : AppBaseViewModel() {

    companion object {
        const val INTERNET = "Internet"
        const val SERVER = "Server"
    }
}
