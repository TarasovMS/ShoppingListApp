package com.persAssistant.shopping_list.ui.fragment.purchase

import android.widget.ArrayAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentPurchase2Binding
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.ui.fragment.purchase.SelectionOfCategoryInDialog.DialogButtonsClickedListener
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding

abstract class PurchaseFragment : AppBaseFragment(R.layout.fragment_purchase2) {

    //TODO сделать спинер

    protected abstract fun createViewModel(): PurchaseViewModel

    //    protected val viewModel: PurchaseViewModel by viewModels { viewModelFactory }
    private val binding: FragmentPurchase2Binding by viewBinding(FragmentPurchase2Binding::bind)
    protected lateinit var viewModel: PurchaseViewModel

    private val categoryClicker = object : DialogButtonsClickedListener {
        override fun okClickListener(category: Category) {
            viewModel.setCategory(category)
        }
    }

    private val unitsSpinnerAdapter by lazy {
        ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.units)
        )
    }

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.fragmentPurchaseToolbar
    }

    override fun initUi() {
        viewModel = createViewModel()

        binding.run {
            vmPurchase = viewModel
            lifecycleOwner = this@PurchaseFragment

            fragmentPurchaseUnitText.apply {
                setAdapter(unitsSpinnerAdapter)

                setOnItemClickListener { _, _, position, _ ->
                    unitsSpinnerAdapter.getItem(position).let { this.setText(it) }
                }
            }
        }
    }

    override fun initListeners() {
        binding.apply {
            fragmentPurchaseCategoriesTil.setOnClickListener {
                SelectionOfCategoryInDialog.show(requireActivity(), categoryClicker)
            }
        }
    }

    override fun initObservers() {
//        viewModel.run {
//            closeEvent.observe(viewLifecycleOwner) {
//                uiRouter.navigateBack()
//            }
//        }
    }

    companion object {
        const val KEY_SHOPPING_LIST_ID = "SHOPPING_LIST_ID"
        const val KEY_PURCHASE_ID = "PURCHASE_ID"
    }
}
