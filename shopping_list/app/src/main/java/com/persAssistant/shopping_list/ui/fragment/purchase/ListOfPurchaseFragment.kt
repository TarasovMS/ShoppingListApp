package com.persAssistant.shopping_list.ui.fragment.purchase

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.databinding.RecyclerPurchaseBinding
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.INVALID_ID
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel.*
import com.persAssistant.shopping_list.util.delegate.viewBinding
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
        viewModel.run {

            fullPurchaseList.observe(viewLifecycleOwner) {
                purchaseAdapter.updateItems(it)
            }

//            deletePurchaseId.observe(viewLifecycleOwner) {
//                purchaseAdapter.removePurchase(it)
//            }
        }
    }

    private fun editItemAdapter(purchase: Purchase) {
        uiRouter.navigateByDirection(
            ListOfPurchaseFragmentDirections.actionPurchaseEditingList(
                purchaseId = purchase.id ?: INVALID_ID
            )
        )
    }
}
