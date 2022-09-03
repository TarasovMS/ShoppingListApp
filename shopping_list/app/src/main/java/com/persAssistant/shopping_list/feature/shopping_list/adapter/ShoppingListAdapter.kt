package com.persAssistant.shopping_list.feature.shopping_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.DATE_FORMAT
import com.persAssistant.shopping_list.databinding.ItemInfoShoppingListBinding
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.util.DiffUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class ShoppingListAdapter(
    private val onShoppingListClickListener: OnShoppingListClickListener,
) : RecyclerView.Adapter<ShoppingListViewHolder>(), DiffUtils {

    private var items: LinkedList<ShoppingList> by Delegates.observable(LinkedList()) { _, old, new ->
        autoNotify(old, new) { oldItem, newItem -> oldItem.id == newItem.id }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shoppingListRecycleView = items[position]
        holder.apply {
            name.text = shoppingListRecycleView.name
            date.text = SimpleDateFormat(DATE_FORMAT, Locale.US).format(shoppingListRecycleView.date)
            bindView(shoppingListRecycleView, onShoppingListClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder(
            ItemInfoShoppingListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun updateItems(items: LinkedList<ShoppingList>) {
        this.items = items
    }

}

class ShoppingListViewHolder(
    val binding: ItemInfoShoppingListBinding
) : RecyclerView.ViewHolder(binding.root) {
    val name: TextView = binding.itemRecyclerInfoShoppingListNameTv
    val date: TextView = binding.itemRecyclerInfoShoppingListDateTv
    val menu: TextView = binding.itemRecyclerInfoShoppingListMenuTv

    fun bindView(
        shoppingList: ShoppingList,
        onShoppingListClickListener: OnShoppingListClickListener
    ) {
        name.setOnClickListener {
            onShoppingListClickListener.clickItem(shoppingList)
        }

        menu.setOnClickListener {
            initPopMenu(
                menu = menu,
                context = it.context,
                shoppingList = shoppingList,
                onShoppingListClickListener = onShoppingListClickListener,
            )
        }
    }

    private fun initPopMenu(
        menu: View,
        context: Context,
        shoppingList: ShoppingList,
        onShoppingListClickListener: OnShoppingListClickListener,
    ) {
        PopupMenu(context, menu).apply {
            inflate(R.menu.options_menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_delete -> onShoppingListClickListener.deleteItem(shoppingList)
                    R.id.menu_edit -> onShoppingListClickListener.editItem(shoppingList)
                }

                false
            }

            show()
        }
    }
}
