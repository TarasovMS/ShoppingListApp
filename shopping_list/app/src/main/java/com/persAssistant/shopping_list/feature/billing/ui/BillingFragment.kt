package com.persAssistant.shopping_list.feature.billing.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentBillingBinding
import com.persAssistant.shopping_list.util.viewBinding

class BillingFragment : AppBaseFragment(R.layout.fragment_billing) {

    private val binding: FragmentBillingBinding by viewBinding(FragmentBillingBinding::bind)
    private val viewModel: BillingViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.purchasedSubscriptionsLiveData.observe(viewLifecycleOwner) { purchases ->
            if (purchases.isNullOrEmpty()) {
                viewModel.getSubscriptions()
            } else {
                Log.d("Billing", "Existing purchases: ${purchases.size}")
            }
        }
    }

}