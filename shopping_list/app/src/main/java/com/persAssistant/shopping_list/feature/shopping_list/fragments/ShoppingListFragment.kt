package com.persAssistant.shopping_list.feature.shopping_list.fragments

import android.app.DatePickerDialog
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentShoppingListBinding
import com.persAssistant.shopping_list.feature.shopping_list.view_model.ShoppingListViewModel
import com.persAssistant.shopping_list.common.DATE_FORMAT
import com.persAssistant.shopping_list.common.DEFAULT_DATE_FORMAT
import com.persAssistant.shopping_list.util.delegate.viewBinding
import com.persAssistant.shopping_list.util.getOrSet
import java.text.SimpleDateFormat
import java.util.*

abstract class ShoppingListFragment : AppBaseFragment(R.layout.fragment_shopping_list) {

    protected abstract fun createViewModel(): ShoppingListViewModel
    private val binding: FragmentShoppingListBinding by viewBinding(FragmentShoppingListBinding::bind)
    protected lateinit var viewModel: ShoppingListViewModel

    override fun initUi() {
        viewModel = createViewModel()

        binding.apply {
            vmShoppingList = viewModel
            lifecycleOwner = this@ShoppingListFragment
        }
    }

    override fun initObservers() {
        viewModel.closeEvent.observe(viewLifecycleOwner) { uiRouter.navigateBack() }
    }

    override fun initListeners() {
        binding.tvDateShoppingList.setOnClickListener { showDateDialog() }
    }

    private fun showDateDialog() {
        val calendar = Calendar.getInstance()
        val date = SimpleDateFormat(DATE_FORMAT).parse(
            viewModel.strDate.value.getOrSet(DEFAULT_DATE_FORMAT)
        )
        date?.let {
            calendar.time = it
        }

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        context?.let {
            val datePickerDialog = DatePickerDialog(it, { _, yearDPD, monthOfYear, dayOfMonth ->
                calendar.set(yearDPD, monthOfYear, dayOfMonth)
                viewModel.date = calendar.time
            }, year, month, day)

            datePickerDialog.show()
        }
    }

}
