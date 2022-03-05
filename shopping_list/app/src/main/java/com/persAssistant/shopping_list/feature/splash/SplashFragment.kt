package com.persAssistant.shopping_list.feature.splash

import android.os.Bundle
import android.util.EventLog
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment

class SplashFragment : AppBaseFragment(R.layout.splash_fragment) {

    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        viewModel.getInitData()
    }

    private fun initObservers() {
        viewModel.initDataReceived.observe(viewLifecycleOwner) {
            if (it) uiRouter.navigateById(R.id.splash_screen_opens_shoppingList)
        }

        // инфа об ошибке

//        viewModel.failureData.observe(viewLifecycleOwner) { event ->
//            event.getEventPayload {
//                showFailure(it)
//            }
//        }
    }
}
