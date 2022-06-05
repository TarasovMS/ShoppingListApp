package com.persAssistant.shopping_list.ui.fragment.purchase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.FragmentPurchaseBinding
import com.persAssistant.shopping_list.ui.fragment.purchase.SelectionOfCategoryInDialog.DialogButtonsClickedListener
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

abstract class PurchaseFragment : AppBaseFragment(R.layout.fragment_purchase) {

    // TODO избавиться от lateInit
    protected abstract fun createViewModel(): PurchaseViewModel
    protected val viewModel: PurchaseViewModel by viewModels { viewModelFactory }
    private val binding: FragmentPurchaseBinding by viewBinding(FragmentPurchaseBinding::bind)
//    protected lateinit var viewModel: PurchaseViewModel

    private val categoryClicker = object : DialogButtonsClickedListener {
        override fun okClickListener(category: Category) {
            viewModel.setCategory(category)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
//        viewModel = createViewModel()

        binding.apply {
            vmPurchase = viewModel
            lifecycleOwner = this@PurchaseFragment

            llSelectionOfCategoriesForPurchases.setOnClickListener {
                SelectionOfCategoryInDialog.show(requireActivity(), categoryClicker)
            }
        }
    }

    private fun initObservers() {
        viewModel.closeEvent.observe(viewLifecycleOwner) {
            uiRouter.navigateBack()
        }
    }

    companion object {
        const val KEY_SHOPPING_LIST_ID = "SHOPPING_LIST_ID"
        const val KEY_PURCHASE_ID = "PURCHASE_ID"
    }
}
