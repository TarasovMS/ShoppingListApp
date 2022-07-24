package com.persAssistant.shopping_list.ui.fragment.purchase

import android.widget.ArrayAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentPurchaseBinding
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.util.ZERO_POSITION
import com.persAssistant.shopping_list.util.delegate.viewBinding
import com.persAssistant.shopping_list.util.updateAdapter

abstract class PurchaseFragment : AppBaseFragment(R.layout.fragment_purchase) {

    protected abstract fun createViewModel(): PurchaseViewModel
    private val binding: FragmentPurchaseBinding by viewBinding(FragmentPurchaseBinding::bind)
    protected lateinit var viewModel: PurchaseViewModel

    private val unitsSpinnerAdapter by lazy {
        context?.let { context ->
            ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.units)
            )
        }
    }

    private val categoriesSpinnerAdapter by lazy {
        context?.let { context ->
            CategoriesSpinnerAdapter(
                context,
                android.R.layout.simple_list_item_1,
                mutableListOf()
            )
        }
    }

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
                    setText(unitsSpinnerAdapter?.getItem(ZERO_POSITION), false)
            }

            fragmentPurchaseCategoriesText.setAdapter(categoriesSpinnerAdapter)
        }
    }

    override fun initListeners() {
        binding.apply {

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

            fragmentPurchaseCategoriesText.run {
                setOnItemClickListener { _, _, position, _ ->
                    categoriesSpinnerAdapter?.getItem(position)?.let {
                        this.setText(it.name)
                        viewModel.setCategory(it)
                    }
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

            allCategories.observe(viewLifecycleOwner) { list ->
                categoriesSpinnerAdapter?.run {
                    updateAdapter(list)

                    if (categoriesSpinnerAdapter?.isEmpty == false) selectedCategory.value?.let { selectedCategory ->
                        val position = this.getPositionItem(selectedCategory)

                        this.getItem(position).let {
                            binding.fragmentPurchaseCategoriesText.setText(it.name, false)
                        }
                    }
                }
            }
        }
    }
}
