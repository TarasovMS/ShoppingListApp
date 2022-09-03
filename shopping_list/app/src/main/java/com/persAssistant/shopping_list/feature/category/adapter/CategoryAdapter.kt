package com.persAssistant.shopping_list.feature.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.databinding.ItemInfoCategoryBinding
import com.persAssistant.shopping_list.domain.entities.Category
import com.persAssistant.shopping_list.util.DiffUtils
import java.util.*
import kotlin.properties.Delegates

class CategoryAdapter(
    private val onCategoryClickListener: OnCategoryClickListener,
) : RecyclerView.Adapter<CategoryViewHolder>(), DiffUtils {

    private var items: LinkedList<Category> by Delegates.observable(LinkedList()) { _, old, new ->
        autoNotify(old, new) { oldItem, newItem -> oldItem.id == newItem.id }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryRecycleView = items[position]

        holder.apply {
            name.text = categoryRecycleView.name
            bindView(categoryRecycleView, onCategoryClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemInfoCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun updateItems(items: LinkedList<Category>) {
        this.items = items
    }

}

class CategoryViewHolder(
    val binding: ItemInfoCategoryBinding
) : RecyclerView.ViewHolder(binding.root){

    val name: TextView = binding.itemRecyclerInfoCategoryNameTv
    val menu: TextView = binding.itemRecyclerInfoCategoryMenuTv

    fun bindView(
        category: Category,
        onCategoryClickListener: OnCategoryClickListener,
    ) {
        name.setOnClickListener { onCategoryClickListener.clickedCategoryItem(category) }

        menu.setOnClickListener {
            initPopMenu(
                context = it.context,
                menu = menu,
                onCategoryClickListener = onCategoryClickListener,
                category = category,
            )
        }
    }

    private fun initPopMenu(
        menu: View,
        context: Context,
        category: Category,
        onCategoryClickListener: OnCategoryClickListener,
    ) {
        PopupMenu(context, menu).apply {
            inflate(R.menu.options_menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_delete -> onCategoryClickListener.deleteItem(category)
                    R.id.menu_edit -> onCategoryClickListener.editItem(category)
                }

                false
            }

            show()
        }
    }
}
