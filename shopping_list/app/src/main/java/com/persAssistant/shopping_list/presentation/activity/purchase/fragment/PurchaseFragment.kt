package com.persAssistant.shopping_list.presentation.activity.purchase.fragment

import android.os.Bundle
import android.view.View
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.FragmentPurchaseBinding
import com.persAssistant.shopping_list.presentation.activity.purchase.PurchaseViewModel
import com.persAssistant.shopping_list.presentation.activity.purchase.SelectionOfCategoryInDialog

abstract class PurchaseFragment: AppBaseFragment(R.layout.fragment_purchase) {

    protected abstract fun createViewModel(): PurchaseViewModel
    private val binding: FragmentPurchaseBinding by lazy { FragmentPurchaseBinding.inflate(layoutInflater) }
    protected lateinit var viewModel: PurchaseViewModel

    companion object {
        const val KEY_SHOPPING_LIST_ID = "SHOPPING_LIST_ID"
        const val KEY_PURCHASE_ID = "PURCHASE_ID"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) {
            //TODO закрытие edited & created
//            finish()
            uiRouter.navigateById(R.id.recyclerView_purchase)
        }

        binding.llSelectionOfCategoriesForPurchases.setOnClickListener {
            SelectionOfCategoryInDialog.show(requireActivity(), object:
                SelectionOfCategoryInDialog.DialogButtonsClickedListener{
                override fun okClickListener(category: Category) {
                    viewModel.setCategory(category)
                }
            })
        }

        binding.vmPurchase = viewModel
        binding.lifecycleOwner = this
    }
}
