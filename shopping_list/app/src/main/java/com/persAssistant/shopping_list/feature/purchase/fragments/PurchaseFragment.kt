package com.persAssistant.shopping_list.feature.purchase.fragments

import android.widget.ArrayAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.common.AppBaseViewModel.ProgressState.FINISHED
import com.persAssistant.shopping_list.databinding.FragmentPurchaseBinding
import com.persAssistant.shopping_list.feature.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.feature.purchase.view_model.PurchaseViewModel.FieldPurchaseValidation.NAME
import com.persAssistant.shopping_list.common.ZERO_POSITION
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.error.RegistrationError.NameValidationError
import com.persAssistant.shopping_list.feature.purchase.adapter.CategoriesSpinnerAdapter
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
        // TODO : мб getCategoriesNames можно улучшить
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

            fragmentPurchaseButtonSavePurchase.setOnClickListener {
                if (viewModel.progressEvent.value == FINISHED)
                    onClickButtonSavePurchase()
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
        with(viewModel) {

            closeEvent.observe(viewLifecycleOwner) {
                uiRouter.navigateBack()
            }

            unit.observe(viewLifecycleOwner) {
                binding.fragmentPurchaseUnitText.setText(it)
            }

            allCategories.observe(viewLifecycleOwner) {
                updateSpinnerAdapter(it)
            }

            errorValidation.observe(viewLifecycleOwner) {
                validationError(it)
            }
        }
    }

    private fun validationError(failure: Failure) {
        when (failure) {
            is NameValidationError -> {
                showFieldError(
                    stringId = failure.getErrorMessageResource(),
                    fieldView = binding.fragmentPurchaseNameProductText,
                )
            }
        }
    }

    private fun updateSpinnerAdapter(list: ArrayList<Category>) {
        categoriesSpinnerAdapter?.run {
            updateAdapter(list)

            if (!isEmpty)
                setTextSelectedCategory(this)
        }
    }

    private fun setTextSelectedCategory(adapter: CategoriesSpinnerAdapter) {
        viewModel.selectedCategory.value?.let { selectedCategory ->
            val position = adapter.getPositionItem(selectedCategory)

            adapter.getItem(position).let {
                binding.fragmentPurchaseCategoriesText.setText(it.name, false)
            }
        }
    }

    private fun onClickButtonSavePurchase() {
        with(viewModel) {
            updateProgressEvent(FINISHED)
            unit.value = binding.fragmentPurchaseUnitText.text.toString()
            validateInputs(
                field = NAME,
                name = binding.fragmentPurchaseNameProductText.text.toString()
            )
        }
    }
}
