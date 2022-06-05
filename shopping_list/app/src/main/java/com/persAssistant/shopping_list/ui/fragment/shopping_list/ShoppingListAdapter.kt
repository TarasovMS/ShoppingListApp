package com.persAssistant.shopping_list.ui.fragment.shopping_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.ShoppingList
import com.persAssistant.shopping_list.util.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

class ShoppingListAdapter(
    private val onShoppingListClickListener: OnShoppingListClickListener,
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    private var items: LinkedList<ShoppingList> = LinkedList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoppingListRecycleView = items[position]
        holder.apply {
            name.text = shoppingListRecycleView.name
            date.text = SimpleDateFormat(DATE_FORMAT).format(shoppingListRecycleView.date)
            bindView(shoppingListRecycleView, onShoppingListClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_info_shopping_list, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.TV_name_recycler_shoppingList)
        val date: TextView = view.findViewById(R.id.TV_date_recycler_shoppingList)
        val menu: TextView = view.findViewById(R.id.TV_list_menu_purchase)

        fun bindView(
            shoppingList: ShoppingList,
            onShoppingListClickListener: OnShoppingListClickListener
        ) {
            name.setOnClickListener {
                onShoppingListClickListener.clickedShoppingListItem(shoppingList)
            }

            menu.setOnClickListener {
                val popup = PopupMenu(it.context, menu)
                popup.inflate(R.menu.options_menu)

                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_delete -> {
                            onShoppingListClickListener.deleteItem(shoppingList)
                        }

                        R.id.menu_edit -> {
                            onShoppingListClickListener.editItem(shoppingList)
                        }
                    }

                    false
                }

                popup.show()
            }
        }
    }

    fun updateItems(items: LinkedList<ShoppingList>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun removeShoppingList(id: Long?) {
        val shoppingListToRemove = items.find { it.id == id }
        items.remove(shoppingListToRemove)
        notifyDataSetChanged()
    }
}
