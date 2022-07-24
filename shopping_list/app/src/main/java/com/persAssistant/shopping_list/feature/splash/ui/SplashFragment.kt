package com.persAssistant.shopping_list.feature.splash.ui

import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.feature.splash.viewmodel.SplashViewModel

class SplashFragment : AppBaseFragment(R.layout.splash_fragment) {

    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun initUi() {
        viewModel.getInitData()
    }

    override fun initObservers() {
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
