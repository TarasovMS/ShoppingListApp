package com.persAssistant.shopping_list.presentation.activity.shopping_list.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.ActivityShoppingListBinding
import com.persAssistant.shopping_list.presentation.activity.shopping_list.ShoppingListViewModel
import java.text.SimpleDateFormat
import java.util.*

abstract class ShoppingListFragment : AppBaseFragment(R.layout.activity_shopping_list) {

    companion object {
        const val SHOPPING_LIST_KEY = "SHOPPINGLIST_ID"
    }

    protected abstract fun createViewModel(): ShoppingListViewModel
    private val binding: ActivityShoppingListBinding by lazy { ActivityShoppingListBinding.inflate(layoutInflater) }

    protected lateinit var viewModel: ShoppingListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) {
            //TODO закрытие Edited & Created
//            finish()
            uiRouter.navigateById(R.id.recycler_view_shopping_list)

        }

        binding.tvDateShoppingList.setOnClickListener {

            val date = SimpleDateFormat("dd.MM.yyyy").parse(viewModel.strDate.value!!)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, yearDPD, monthOfYear, dayOfMonth ->
                calendar.set(yearDPD,monthOfYear,dayOfMonth)
                viewModel.date = calendar.time
            }, year, month, day)

            datePickerDialog.show()
        }
        binding.vmShoppingList = viewModel
        binding.lifecycleOwner = this
    }
}
