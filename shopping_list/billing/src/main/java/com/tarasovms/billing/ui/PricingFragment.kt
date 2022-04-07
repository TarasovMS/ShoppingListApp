package com.tarasovms.billing.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentBillingBinding
import com.persAssistant.shopping_list.util.viewBinding

class PricingFragment : AppBaseFragment(R.layout.fragment_billing) {

    companion object{
        const val TAG = "Billing"
    }

    private val binding: FragmentBillingBinding by viewBinding(FragmentBillingBinding::bind)
    private val viewModel: PricingViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.purchasedSubscriptionsLiveData.observe(viewLifecycleOwner) { purchases ->
            if (purchases.isNullOrEmpty()) {
                viewModel.getSubscriptions()
            } else {
                Log.d(TAG, "Существующие покупки: ${purchases.size}")
            }
        }

        viewModel.subscriptionsLiveData.observe(viewLifecycleOwner) { subscriptions ->
            if (subscriptions != null) {
                for (subscription in subscriptions) {
                    Log.d(TAG, subscription.toString())
                }
                setProductsList(subscriptions)
            } else {
                Log.d(TAG, "По запросу не найден код ")
                binding.fragmentBillingEmpty.isVisible = true
                binding.fragmentBillingProgressBar.isVisible = false
            }
        }

        viewModel.purchasesUpdateLiveData.observe(viewLifecycleOwner) { purchases ->
            acknowledgePurchases(purchases)
        }

    }

    /**
     * Подтведжение покупки
     */
    private fun acknowledgePurchases(purchases: List<Purchase>?) {
        purchases?.forEach {
            viewModel.acknowledgePurchase(it)
        }
    }

    /**
     * Запускаем процесс покупки
     */
    private fun launchPurchaseFlow(product: SkuDetails) {
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(product)
            .build()

//        val billingClient = viewModel.getBillingClient()
//        val responseCode = billingClient.launchBillingFlow(requireActivity(), flowParams)
//        Log.i(TAG, "launchPurchaseFlow result ${responseCode.responseCode}")
    }

    /**
     * Установливаем список продуктов
     */
    private fun setProductsList(products: List<SkuDetails>) {
        binding.fragmentBillingProgressBar.isVisible = false
        binding.fragmentBillingEmpty.isVisible = false
        binding.fragmentBillingProductsList.adapter = ProductAdapter(products) { skuDetails ->
            launchPurchaseFlow(skuDetails)
        }
    }

}