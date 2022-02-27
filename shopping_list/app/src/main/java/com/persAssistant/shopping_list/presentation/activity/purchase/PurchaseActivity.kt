package com.persAssistant.shopping_list.presentation.activity.purchase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.FragmentPurchaseBinding
import java.util.*

abstract class PurchaseActivity: AppCompatActivity() {
    protected abstract fun createViewModel(): PurchaseViewModel
    private val binding: FragmentPurchaseBinding by lazy { FragmentPurchaseBinding.inflate(layoutInflater) }
    protected lateinit var viewModel: PurchaseViewModel

    companion object {
        const val KEY_SHOPPING_LIST_ID = "SHOPPING_LIST_ID"
        const val KEY_PURCHASE_ID = "PURCHASE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel = createViewModel()
        viewModel.closeEvent.observe(this) { finish() }

        binding.llSelectionOfCategoriesForPurchases.setOnClickListener {
            SelectionOfCategoryInDialog.show(this@PurchaseActivity, object:
                SelectionOfCategoryInDialog.DialogButtonsClickedListener{
                override fun okClickListener(category: Category) {
                    viewModel.setCategory(category)
                }
            })
        }

        binding.vmPurchase = viewModel
        binding.lifecycleOwner = this
    }
}
