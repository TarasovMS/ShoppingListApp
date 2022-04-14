package com.persAssistant.shopping_list.ui.fragment.purchase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.databinding.RecyclerPurchaseBinding
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.ui.fragment.purchase.PurchaseFragment.Companion.KEY_PURCHASE_ID
import com.persAssistant.shopping_list.ui.fragment.purchase.PurchaseFragment.Companion.KEY_SHOPPING_LIST_ID
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel.*
import com.persAssistant.shopping_list.util.delegate.viewBinding
import java.lang.Exception

class ListOfPurchaseFragment : AppBaseFragment(R.layout.recycler_purchase) {

    //TODO избавиться от lateinit через делегат and !!
    private lateinit var purchaseAdapter: PurchaseAdapter
    private val binding: RecyclerPurchaseBinding by viewBinding(RecyclerPurchaseBinding::bind)
    private val viewModel: ListOfPurchaseViewModel by viewModels { viewModelFactory }

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.recyclerPurchaseToolbar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObserve()
    }

    private fun init() {
        purchaseAdapter = PurchaseAdapter(object : OnPurchaseClickListener {
            override fun clickedPurchaseItem(purchase: Purchase) {}
            override fun deleteItem(purchase: Purchase) {
                viewModel.deleteItemPurchase(purchase)
            }

            override fun editItem(purchase: Purchase) {
                val bundle = Bundle()
                bundle.putLong(KEY_PURCHASE_ID, purchase.id!!)
                uiRouter.navigateById(R.id.editPurchase, bundle)
            }
        })

        binding.recyclerViewPurchase.adapter = purchaseAdapter

        val parentId = arguments
            ?.getLong(KEY_PARENT_ID)
            ?: throw Exception(" Error in ListOfPurchaseActivity absent parentId ")

        val idTypeIndex = arguments
            ?.getInt(KEY_INDEX_TYPE)
            ?: throw Exception(" Error in ListOfPurchaseActivity absent idTypeIndex ")

        val type = IdTypes.values()[idTypeIndex]
        viewModel.init(this, parentId, type)

        val buttonVisible = arguments?.getBoolean(KEY_VISIBILITY_BUTTON)
        if (buttonVisible == true)
            binding.recyclerPurchaseBtnAdd.visibility = View.VISIBLE

        binding.recyclerPurchaseBtnAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong(KEY_SHOPPING_LIST_ID, parentId)
            uiRouter.navigateById(R.id.createPurchase, bundle)
        }
    }

    private fun initObserve() {
        viewModel.fullPurchaseList.observe(viewLifecycleOwner) {
            purchaseAdapter.updateItems(it)
        }

        viewModel.deletePurchaseId.observe(viewLifecycleOwner) {
            purchaseAdapter.removePurchase(it)
        }
    }
}
