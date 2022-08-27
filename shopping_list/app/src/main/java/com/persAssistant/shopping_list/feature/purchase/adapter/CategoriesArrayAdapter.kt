package com.persAssistant.shopping_list.feature.purchase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.persAssistant.shopping_list.domain.entities.Category

class CategoriesSpinnerAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val listCategory: List<Category>
) : ArrayAdapter<Category>(
    context,
    layoutResource,
    listCategory
) {
    private val inflater: LayoutInflater

    init {
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return listCategory.size
    }

    override fun getItem(position: Int): Category {
        return listCategory[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getPositionItem(category: Category): Int {
        return listCategory.indexOf(category)
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view: TextView = convertView as TextView?
            ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView

        view.text = listCategory[position].name

        return view
    }
}
//2576DSFilter
