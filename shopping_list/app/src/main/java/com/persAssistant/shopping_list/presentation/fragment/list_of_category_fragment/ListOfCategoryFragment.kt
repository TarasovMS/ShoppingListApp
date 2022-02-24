package com.persAssistant.shopping_list.presentation.fragment.list_of_category_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.databinding.RecyclerCategoryBinding
import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.category.CreatorCategoryActivity
import com.persAssistant.shopping_list.presentation.activity.category.EditorCategoryActivity
import com.persAssistant.shopping_list.presentation.fragment.list_of_purchase_fragment.ListOfPurchaseViewModel
import com.persAssistant.shopping_list.presentation.util.viewBinding
import java.util.*

class ListOfCategoryFragment: AppBaseFragment(R.layout.recycler_category) {


    private val ui: RecyclerCategoryBinding by viewBinding(RecyclerCategoryBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }



//    private lateinit var viewModel: ListOfCategoryViewModel

//    private lateinit var ui: RecyclerCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        ui = RecyclerCategoryBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        // initialize Adapter
        categoryAdapter = CategoryAdapter(LinkedList(), object : OnCategoryClickListener {
            override fun clickedCategoryItem(category: Category) {
//                val intent = ListOfPurchaseActivity.getIntentByCategoryId(requireContext(),category.id!!)
//                val intent = ListOfPurchaseFragment.getIntentByCategoryId(requireContext(), category.id!!)
//                startActivity(intent)

                bundle.putLong(KEY_PARENT_ID, category.id!!)
                bundle.putBoolean(KEY_VISIBILITY_BUTTON, false)
                bundle.putInt(KEY_INDEX_TYPE, ListOfPurchaseViewModel.IdTypes.CATEGORY.ordinal)


//                val fragmentManager = childFragmentManager.beginTransaction()
//                fragmentManager.replace(R.id.fragment,ListOfPurchaseFragment())
//                fragmentManager.commit()



            }

            override fun deleteItem(category: Category) {
                viewModel.deleteItemCategory(category)
            }

            override fun editItem(category: Category) {
                val intent = EditorCategoryActivity.getIntent(requireContext(), category.id!!)
                startActivity(intent)
            }
        })
        ui.recyclerViewCategory.adapter = categoryAdapter

        // initialize ViewModel
//        viewModel = app.appComponent.getListOfCategoryViewModel()

        viewModel.deleteCategoryId.observe(requireActivity(), androidx.lifecycle.Observer {
            categoryAdapter.removeCategory(it)
        })

        viewModel.categoryList.observe(requireActivity(), androidx.lifecycle.Observer {
            categoryAdapter.updateItems(it)
        })

        viewModel.init(this)

        ui.btnAddCategory.setOnClickListener {
            val intent = CreatorCategoryActivity.getIntent(requireContext())
            startActivity(intent)
        }

//        return ui.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        app = (context.applicationContext as App)
    }
}