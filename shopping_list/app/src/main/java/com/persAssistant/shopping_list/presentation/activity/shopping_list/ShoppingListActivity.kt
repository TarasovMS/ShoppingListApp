package com.persAssistant.shopping_list.presentation.activity.shopping_list

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.persAssistant.shopping_list.databinding.FragmentShoppingListBinding
import java.text.SimpleDateFormat
import java.util.*

abstract class ShoppingListActivity : AppCompatActivity() {

    companion object {
        const val SHOPPING_LIST_KEY = "SHOPPINGLIST_ID"
    }

    protected abstract fun createViewModel(): ShoppingListViewModel
    private val binding: FragmentShoppingListBinding by lazy { FragmentShoppingListBinding.inflate(layoutInflater) }

    protected lateinit var viewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) { finish() }

        binding.tvDateShoppingList.setOnClickListener {

            val date = SimpleDateFormat("dd.MM.yyyy").parse(viewModel.strDate.value!!)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, yearDPD, monthOfYear, dayOfMonth ->
                calendar.set(yearDPD,monthOfYear,dayOfMonth)
                viewModel.date = calendar.time
            }, year, month, day)

            datePickerDialog.show()
        }
        binding.vmShoppingList = viewModel
        binding.lifecycleOwner = this
    }
}
