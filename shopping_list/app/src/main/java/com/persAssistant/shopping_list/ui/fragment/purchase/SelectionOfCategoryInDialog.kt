package com.persAssistant.shopping_list.ui.fragment.purchase

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.ui.App
import com.persAssistant.shopping_list.ui.fragment.category.OnCategoryClickListener
import com.persAssistant.shopping_list.ui.fragment.category.CategoryAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SelectionOfCategoryInDialog {

    interface DialogButtonsClickedListener {
        fun okClickListener(category: Category)
    }

    companion object {
        fun show(activity: Activity, showDialog: DialogButtonsClickedListener) {
            val builder = AlertDialog.Builder(activity)
            val inflater: LayoutInflater = activity.layoutInflater
            val dialogView: View = inflater.inflate(R.layout.custom_dialog, null)

            builder.setView(dialogView)
            builder.create()
            val mAlertDialog = builder.show()

            val recyclerView: RecyclerView = dialogView.findViewById(R.id.recyclerView_category_custom_dialog)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.itemAnimator = DefaultItemAnimator()

            val categoryAdapter = CategoryAdapter(object : OnCategoryClickListener {
                override fun clickedCategoryItem(category: Category) {
                    showDialog.okClickListener(category)
                    mAlertDialog.dismiss()
                }

                override fun deleteItem(category: Category) {}
                override fun editItem(category: Category) {}
            })

            initAdapter(activity, categoryAdapter)
            recyclerView.adapter = categoryAdapter
        }

        private fun initAdapter(activity: Activity, categoryAdapter: CategoryAdapter) {
            val app = (activity.applicationContext as App)

            app.appComponent.getCategoryInteractor().getAll()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { categoryAdapter.updateItems(it) },
                    {/*Ошибка*/ }
                )
        }
    }
}
