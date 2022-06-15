package com.persAssistant.shopping_list.ui.fragment.purchase

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.databinding.RecyclerPurchaseBinding
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.ui.fragment.purchase.PurchaseFragment.Companion.KEY_PURCHASE_ID
import com.persAssistant.shopping_list.ui.fragment.purchase.PurchaseFragment.Companion.KEY_SHOPPING_LIST_ID
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel.*
import com.persAssistant.shopping_list.util.delegate.viewBinding
import com.persAssistant.shopping_list.util.visible
import java.lang.Exception

class ListOfPurchaseFragment : AppBaseFragment(R.layout.recycler_purchase) {

    private val purchaseAdapter by lazy { PurchaseAdapter(purchaseClick) }
    private val binding: RecyclerPurchaseBinding by viewBinding(RecyclerPurchaseBinding::bind)
    private val viewModel: ListOfPurchaseViewModel by viewModels { viewModelFactory }

    private val parentId by lazy {
        arguments?.getLong(KEY_PARENT_ID)
            ?: throw Exception(getString(R.string.error_parentId_in_purchase_activity))
    }

    private val purchaseClick = object : OnPurchaseClickListener {
        override fun clickedPurchaseItem(purchase: Purchase) {}

        override fun deleteItem(purchase: Purchase) {
            viewModel.deleteItemPurchase(purchase)
        }

        override fun editItem(purchase: Purchase) {
            editItemAdapter(purchase)
        }
    }

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.recyclerPurchaseToolbar
    }

    override fun initUi() {
        binding.recyclerViewPurchase.adapter = purchaseAdapter

        val idTypeIndex = arguments?.getInt(KEY_INDEX_TYPE)
            ?: throw Exception(getString(R.string.error_idTypeIndex_in_purchase_activity))

        val type = IdTypes.values()[idTypeIndex]
        viewModel.init(this, parentId, type)

        val buttonVisible = arguments?.getBoolean(KEY_VISIBILITY_BUTTON)
        if (buttonVisible == true) binding.recyclerPurchaseBtnAdd.visible()
    }

    override fun initListeners() {
        binding.recyclerPurchaseBtnAdd.setOnClickListener {
            uiRouter.navigateById(R.id.createPurchase, Bundle().apply {
                putLong(KEY_SHOPPING_LIST_ID, parentId)
            })
        }
    }

    override fun initObservers() {
        viewModel.run {
            fullPurchaseList.observe(viewLifecycleOwner) {
                purchaseAdapter.updateItems(it)
            }

            deletePurchaseId.observe(viewLifecycleOwner) {
                purchaseAdapter.removePurchase(it)
            }
        }
    }

    private fun editItemAdapter(purchase: Purchase) {
        uiRouter.navigateById(R.id.editPurchase, Bundle().apply {
            putLong(KEY_PURCHASE_ID, purchase.id ?: DEFAULT_CATEGORIES_COUNT)
        })
    }
}
