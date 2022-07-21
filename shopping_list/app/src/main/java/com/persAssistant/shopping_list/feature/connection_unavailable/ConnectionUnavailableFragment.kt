package com.persAssistant.shopping_list.feature.connection_unavailable

import androidx.annotation.ColorRes
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentConnectionUnavailableBinding
import com.persAssistant.shopping_list.feature.connection_unavailable.ConnectionUnavailableViewModel.Companion.INTERNET
import com.persAssistant.shopping_list.feature.connection_unavailable.ConnectionUnavailableViewModel.Companion.SERVER
import com.persAssistant.shopping_list.util.delegate.viewBinding

//todo сделать проверку на интернет чтобы при изменение отслеживалось
class ConnectionUnavailableFragment : AppBaseFragment(R.layout.fragment_connection_unavailable){

    private val binding by viewBinding(FragmentConnectionUnavailableBinding::bind)
    private val viewModel: ConnectionUnavailableViewModel by viewModels { viewModelFactory }

    private val error by lazy {
        arguments?.getString(INTERNET)
            ?: arguments?.getString(SERVER)
            ?: INTERNET
    }

    @ColorRes
    override fun statusBarColor() = R.color.blue

    override fun initUi() {
        if (error == INTERNET) showNoInternetConnection() else showNoServerConnection()
    }

    private fun showNoInternetConnection() {
        binding.fragmentNoConnectionTitle.text = getString(R.string.check_connection_internet)
        binding.fragmentNoConnectionInfo.text = getString(R.string.no_internet_connection)
    }

    private fun showNoServerConnection() {
        binding.fragmentNoConnectionTitle.text = getString(R.string.server_unavailable)
        binding.fragmentNoConnectionInfo.text = getString(R.string.please_try_again_later)
    }
}
