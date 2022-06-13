package com.persAssistant.shopping_list.ui.fragment.purchase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentPurchase2Binding
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.FragmentPurchaseBinding
import com.persAssistant.shopping_list.ui.fragment.purchase.SelectionOfCategoryInDialog.DialogButtonsClickedListener
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

abstract class PurchaseFragment : AppBaseFragment(R.layout.fragment_purchase2) {

    protected abstract fun createViewModel(): PurchaseViewModel
//    protected val viewModel: PurchaseViewModel by viewModels { viewModelFactory }
    private val binding: FragmentPurchase2Binding by viewBinding(FragmentPurchase2Binding::bind)
    protected lateinit var viewModel: PurchaseViewModel

    private val categoryClicker = object : DialogButtonsClickedListener {
        override fun okClickListener(category: Category) {
            viewModel.setCategory(category)
        }
    }

    override fun initUi() {
        viewModel = createViewModel()

        binding.apply {
            vmPurchase = viewModel
            lifecycleOwner = this@PurchaseFragment
        }
    }

    override fun initListeners() {
//        binding.llSelectionOfCategoriesForPurchases.setOnClickListener {
//            SelectionOfCategoryInDialog.show(requireActivity(), categoryClicker)
//        }

        // if fragmentPurchase2
        binding.fragmentPurchaseCategoriesTil.setOnClickListener {
            SelectionOfCategoryInDialog.show(requireActivity(), categoryClicker)
        }
    }

    override fun initObservers() {
        viewModel.closeEvent.observe(viewLifecycleOwner) {
            uiRouter.navigateBack()
        }
    }

    companion object {
        const val KEY_SHOPPING_LIST_ID = "SHOPPING_LIST_ID"
        const val KEY_PURCHASE_ID = "PURCHASE_ID"
    }
}
