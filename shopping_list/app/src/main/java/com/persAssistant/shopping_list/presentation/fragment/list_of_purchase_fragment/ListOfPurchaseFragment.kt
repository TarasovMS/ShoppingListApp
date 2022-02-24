package com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.databinding.RecyclerPurchaseBinding
import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.presentation.activity.purchase.CreatorPurchaseActivity
import com.persAssistant.shopping_list.presentation.activity.purchase.EditorPurchaseActivity
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel.*
import com.persAssistant.shopping_list.presentation.util.viewBinding
import java.lang.Exception
import java.util.*

class ListOfPurchaseFragment: AppBaseFragment(R.layout.recycler_purchase) {

    private lateinit var purchaseAdapter: PurchaseAdapter
//    protected lateinit var ui: RecyclerPurchaseBinding
//    protected lateinit var viewModel: ListOfPurchaseViewModel

    private val binding: RecyclerPurchaseBinding by viewBinding(RecyclerPurchaseBinding::bind)
    private val viewModel: ListOfPurchaseViewModel by viewModels { viewModelFactory }



//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        super.onCreateView(inflater, container, savedInstanceState)
//        return DataBindingUtil.setContentView<ViewDataBinding>(requireActivity(), R.layout
//            .recycler_purchase).root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ui = RecyclerPurchaseBinding.inflate(layoutInflater)

//        viewModel = app.appComponent.getListOfPurchaseViewModel()

        purchaseAdapter = PurchaseAdapter(LinkedList(), object : OnPurchaseClickListener {
            override fun clickedPurchaseItem(purchase: Purchase) {}

            override fun deleteItem(purchase: Purchase) {
                viewModel.deleteItemPurchase(purchase)
            }

            override fun editItem(purchase: Purchase) {
                val intent = EditorPurchaseActivity.getIntent(requireContext(), purchase.id!!)
                startActivity(intent)
            }
        })
        ui.recyclerViewPurchase.adapter = purchaseAdapter

        viewModel.fullPurchaseList.observe(this, androidx.lifecycle.Observer {
            purchaseAdapter.updateItems(it)
        })

        viewModel.deletePurchaseId.observe(this, androidx.lifecycle.Observer {
            purchaseAdapter.removePurchase(it)
        })

        val parentId = requireActivity().intent.getLongExtra(KEY_PARENT_ID, -1)
        if (parentId == -1L)
            throw Exception (" Ошибка в ListOfPurchaseActivity отсутствует parentId ")

        val idTypeIndex =  requireActivity().intent.getIntExtra(KEY_INDEX_TYPE, -1)
        if (idTypeIndex == -1)
            throw Exception (" Ошибка в ListOfPurchaseActivity отсутствует idTypeIndex ")

        val type = IdTypes.values()[idTypeIndex]
        viewModel.init(this, parentId, type)

        val buttonAdd: FloatingActionButton = ui.btnAddPurchase
        val buttonVisible = requireActivity().intent.getBooleanExtra(KEY_VISIBILITY_BUTTON,false)
        if (buttonVisible)
            buttonAdd.visibility = View.VISIBLE

        buttonAdd.setOnClickListener {
            val intent = CreatorPurchaseActivity.getIntent(requireContext(), requireActivity().intent
                .getLongExtra(KEY_PARENT_ID, -1))
            startActivity(intent)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        app = (context.applicationContext as App)
    }
}