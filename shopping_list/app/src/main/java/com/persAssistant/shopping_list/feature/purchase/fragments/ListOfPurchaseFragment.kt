package com.persAssistant.shopping_list.feature.purchase.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.databinding.RecyclerPurchaseBinding
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.feature.purchase.adapter.OnPurchaseClickListener
import com.persAssistant.shopping_list.feature.purchase.adapter.PurchaseAdapter
import com.persAssistant.shopping_list.feature.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.feature.purchase.view_model.ListOfPurchaseViewModel.*
import com.persAssistant.shopping_list.util.delegate.viewBinding
import com.persAssistant.shopping_list.util.getOrSet
import com.persAssistant.shopping_list.util.visible

class ListOfPurchaseFragment : AppBaseFragment(R.layout.recycler_purchase) {

    private val purchaseAdapter by lazy { PurchaseAdapter(purchaseClick) }
    private val binding by viewBinding(RecyclerPurchaseBinding::bind)
    private val viewModel: ListOfPurchaseViewModel by viewModels { viewModelFactory }
    private val args: ListOfPurchaseFragmentArgs by navArgs()

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
        val type = IdTypes.values()[args.indexType.toInt()]
        if (args.visibleButtonFab) binding.recyclerPurchaseBtnAdd.visible()
        binding.recyclerViewPurchase.adapter = purchaseAdapter
        viewModel.init(this, args.parentId, type)
    }

    override fun initListeners() {
        binding.recyclerPurchaseBtnAdd.setOnClickListener {
            uiRouter.navigateByDirection(
                ListOfPurchaseFragmentDirections.actionPurchaseCreatingList(
                    listId = args.parentId
                )
            )
        }
    }

    override fun initObservers() {
        viewModel.fullPurchaseList.observe(viewLifecycleOwner) {
            purchaseAdapter.updateItems(it)
        }
    }

    private fun editItemAdapter(purchase: Purchase) {
        uiRouter.navigateByDirection(
            ListOfPurchaseFragmentDirections.actionPurchaseEditingList(
                purchaseId = purchase.id.getOrSet(DEFAULT_INVALID_ID)
            )
        )
    }
}
