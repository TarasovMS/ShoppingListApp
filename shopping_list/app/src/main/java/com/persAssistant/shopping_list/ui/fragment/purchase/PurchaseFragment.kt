package com.persAssistant.shopping_list.ui.fragment.purchase

import android.util.Log
import android.widget.ArrayAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentPurchase2Binding
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.ui.fragment.purchase.SelectionOfCategoryInDialog.DialogButtonsClickedListener
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding
import java.util.ArrayList

abstract class PurchaseFragment : AppBaseFragment(R.layout.fragment_purchase2) {

    //TODO сделать спинер для категорий и переименоватаь лаяут надо кастомизировать спинер для
    // категорий чтобы сохранялся и айди

    protected abstract fun createViewModel(): PurchaseViewModel
    private val binding: FragmentPurchase2Binding by viewBinding(FragmentPurchase2Binding::bind)
    protected lateinit var viewModel: PurchaseViewModel

    private val categoryClicker = object : DialogButtonsClickedListener {
        override fun okClickListener(category: Category) {
            viewModel.setCategory(category)
        }
    }

    private val unitsSpinnerAdapter by lazy {
        context?.let { context ->
            ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.units)
            )
        }
    }

    private var categoriesSpinnerAdapter: ArrayAdapter<String>? = null

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.fragmentPurchaseToolbar
    }

    override fun initUi() {
        viewModel = createViewModel()
        viewModel.getCategoriesNames()

        binding.run {
            vmPurchase = viewModel

            lifecycleOwner = this@PurchaseFragment

            fragmentPurchaseUnitText.apply {
                setAdapter(unitsSpinnerAdapter)
                if (viewModel.unit.value.isNullOrEmpty())
                    setText(unitsSpinnerAdapter?.getItem(0), false)
            }
        }
    }

    override fun initListeners() {
        binding.apply {
            fragmentPurchaseCategoriesTil.setOnClickListener {
                activity?.let { SelectionOfCategoryInDialog.show(it, categoryClicker) }
            }

            fragmentPurchaseUnitText.run {
                setOnItemClickListener { _, _, position, _ ->
                    unitsSpinnerAdapter?.getItem(position).let { this.setText(it) }
                }
            }

            fragmentPurchaseButtonSaveShoppingList.setOnClickListener {
                viewModel.run {
                    unit.value = fragmentPurchaseUnitText.text.toString()
                    save()
                }
            }
        }
    }

    override fun initObservers() {
        viewModel.run {
            closeEvent.observe(viewLifecycleOwner) {
                uiRouter.navigateBack()
            }

            unit.observe(viewLifecycleOwner) {
                binding.fragmentPurchaseUnitText.setText(it)
            }

            categoriesNames.observe(viewLifecycleOwner) {
                setAdapterForCategoriesNames(it)
                Log.w("test"," purchaseFragment initObservers = $it")

                binding.fragmentPurchaseCategoriesText.apply {
                    setAdapter(categoriesSpinnerAdapter)
                    Log.w("test"," purchaseFragment setAdapter = $it")

//                    if (viewModel.unit.value.isNullOrEmpty())
                        setText(categoriesSpinnerAdapter?.getItem(0), false)
                }
            }
        }
    }

    private fun setAdapterForCategoriesNames(list: ArrayList<String>) {
        categoriesSpinnerAdapter = context?.let { context ->
            ArrayAdapter(context, android.R.layout.simple_list_item_1, list)
        }
    }
}
