package com.persAssistant.shopping_list.feature.billing.ui

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentBillingBinding
import com.persAssistant.shopping_list.util.delegate.viewBinding

class PricingFragment : AppBaseFragment(R.layout.fragment_billing) {

    //TODO заменить !! и латинит
    private val binding: FragmentBillingBinding by viewBinding(FragmentBillingBinding::bind)
    private val viewModel: PricingViewModel by viewModels { viewModelFactory }

    private lateinit var billingClientWrapper: BillingClientWrapper2
    private lateinit var productAdapter: ProductAdapter

    override fun initObservers() {
        viewModel.purchasedSubscriptionsLiveData.observe(viewLifecycleOwner) { purchases ->
            if (purchases.isNullOrEmpty()) viewModel.getSubscriptions()
            else Log.d(BILLING, "Существующие покупки: ${purchases.size}")
        }

        viewModel.subscriptionsLiveData.observe(viewLifecycleOwner) { subscriptions ->
            if (subscriptions != null) {
                for (subscription in subscriptions) {
                    Log.d(BILLING, subscription.toString())
                }
                setProductsList(subscriptions)
            } else {
                Log.d(BILLING, "По запросу не найден код ")
                binding.fragmentBillingEmpty.isVisible = true
                binding.fragmentBillingProgressBar.isVisible = false
            }
        }

        viewModel.purchasesUpdateLiveData.observe(viewLifecycleOwner) { purchases ->
            acknowledgePurchases(purchases)
        }
    }

    override fun initUi() {
        displayProducts()

        billingClientWrapper = BillingClientWrapper2(requireContext())

        billingClientWrapper.onPurchaseListener = (object : BillingClientWrapper2
        .OnPurchaseListener {

            override fun onPurchaseSuccess(purchase: Purchase?) {
                TODO("Not yet implemented")
            }

            override fun onPurchaseFailure(error: BillingClientWrapper2.Error) {
                TODO("Not yet implemented")
            }
        })
    }


    private fun displayProducts() {
        billingClientWrapper.queryProducts(object : BillingClientWrapper2.OnQueryProductsListener {
            override fun onSuccess(products: List<SkuDetails>) {
//                products.forEach { product ->
//                    purchaseButtonsMap[product.sku]?.apply {
//                        text = "${product.description} for ${product.price}"
//                        setOnClickListener {
//                            billingClientWrapper.purchase(this@PaywallActivity, product) //will be declared below
//                        }
//                    }
//                }

                productAdapter = ProductAdapter(products)
                binding.fragmentBillingProductsList.adapter = productAdapter


            }

            override fun onFailure(error: BillingClientWrapper2.Error) {
                //handle error
            }
        })
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
//        binding.fragmentBillingProductsList.adapter = ProductAdapter(products) { skuDetails ->
//            launchPurchaseFlow(skuDetails)
//        }
    }

    companion object {
        const val BILLING = "Billing"
    }
}
