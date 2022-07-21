package com.persAssistant.shopping_list.ui.fragment.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.databinding.ItemInfoCategoryBinding
import com.persAssistant.shopping_list.domain.entities.Category
import java.util.*

class CategoryAdapter(
    private val onCategoryClickListener: OnCategoryClickListener,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var items: LinkedList<Category> = LinkedList<Category>()

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

    class CategoryViewHolder(
        val binding: ItemInfoCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.itemRecyclerInfoCategoryNameTv
        val menu: TextView = binding.itemRecyclerInfoCategoryMenuTv

        fun bindView(category: Category, onCategoryClickListener: OnCategoryClickListener) {

            name.setOnClickListener {
                onCategoryClickListener.clickedCategoryItem(category)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, menu).apply {
                    inflate(R.menu.options_menu)

                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {

                            R.id.menu_delete -> {
                                onCategoryClickListener.deleteItem(category)
                            }

                            R.id.menu_edit -> {
                                onCategoryClickListener.editItem(category)
                            }
                        }

                        false
                    }

                    show()
                }
            }
        }
    }

    fun updateItems(items: LinkedList<Category>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun removeCategory(id: Long?) {
        val categoryToRemove = items.find { it.id == id }
        items.remove(categoryToRemove)
        notifyDataSetChanged()
    }
}
