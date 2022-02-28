package com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.databinding.RecyclerPurchaseBinding
import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.presentation.activity.purchase.fragment.PurchaseFragment
import com.persAssistant.shopping_list.presentation.activity.purchase.fragment.PurchaseFragment.Companion.KEY_PURCHASE_ID
import com.persAssistant.shopping_list.presentation.activity.purchase.fragment.PurchaseFragment.Companion.KEY_SHOPPING_LIST_ID
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel.*
import com.persAssistant.shopping_list.presentation.util.viewBinding
import java.lang.Exception
import java.util.*

class ListOfPurchaseFragment: AppBaseFragment(R.layout.recycler_purchase) {

    private lateinit var purchaseAdapter: PurchaseAdapter
    private val binding: RecyclerPurchaseBinding by viewBinding(RecyclerPurchaseBinding::bind)
    private val viewModel: ListOfPurchaseViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        purchaseAdapter = PurchaseAdapter(object : OnPurchaseClickListener {
            override fun clickedPurchaseItem(purchase: Purchase) {}
            override fun deleteItem(purchase: Purchase) { viewModel.deleteItemPurchase(purchase) }
            override fun editItem(purchase: Purchase) {
                val bundle = Bundle()
                bundle.putLong(KEY_PURCHASE_ID,purchase.id!!)
                uiRouter.navigateById(R.id.editPurchase,bundle)
            }
        })
        binding.recyclerViewPurchase.adapter = purchaseAdapter

        viewModel.fullPurchaseList.observe(this) {
            purchaseAdapter.updateItems(it)
        }

        viewModel.deletePurchaseId.observe(this) {
            purchaseAdapter.removePurchase(it)
        }

        val parentId = arguments
            ?.getLong(KEY_PARENT_ID)
            ?: throw Exception (" Error in ListOfPurchaseActivity absent parentId ")

        val idTypeIndex = arguments
            ?.getInt(KEY_INDEX_TYPE)
            ?: throw Exception (" Error in ListOfPurchaseActivity absent idTypeIndex ")

        val type = IdTypes.values()[idTypeIndex]
        viewModel.init(this, parentId, type)

        val buttonVisible = arguments?.getBoolean(KEY_VISIBILITY_BUTTON)
        if (buttonVisible == true)
            binding.btnAddPurchase.visibility = View.VISIBLE

        binding.btnAddPurchase.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong(KEY_SHOPPING_LIST_ID,parentId)
            uiRouter.navigateById(R.id.createPurchase, bundle)
        }
    }
}