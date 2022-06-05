package com.persAssistant.shopping_list.ui.fragment.shopping_list

import android.app.DatePickerDialog
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentShoppingListBinding
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.ShoppingListViewModel
import com.persAssistant.shopping_list.util.DATE_FORMAT
import com.persAssistant.shopping_list.util.delegate.viewBinding
import java.text.SimpleDateFormat
import java.util.*

abstract class ShoppingListFragment : AppBaseFragment(R.layout.fragment_shopping_list) {

    protected abstract fun createViewModel(): ShoppingListViewModel
    private val binding: FragmentShoppingListBinding by viewBinding(FragmentShoppingListBinding::bind)

    protected lateinit var viewModel: ShoppingListViewModel

    override fun initUI(){
        viewModel = createViewModel()
        viewModel.closeEvent.observe(viewLifecycleOwner) { uiRouter.navigateBack() }

        binding.apply {
            vmShoppingList = viewModel
            lifecycleOwner = this@ShoppingListFragment

            tvDateShoppingList.setOnClickListener {

                val date = SimpleDateFormat(DATE_FORMAT).parse(viewModel.strDate.value!!)
                val calendar = Calendar.getInstance()
                calendar.time = date!!
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(requireContext(), { _, yearDPD, monthOfYear, dayOfMonth ->
                    calendar.set(yearDPD, monthOfYear, dayOfMonth)
                    viewModel.date = calendar.time
                }, year, month, day)

                datePickerDialog.show()
            }
        }
    }

    companion object {
        const val SHOPPING_LIST_KEY = "SHOPPING_LIST_ID"
    }
}
