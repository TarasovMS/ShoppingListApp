package com.persAssistant.shopping_list.ui.fragment.purchase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.util.RUSSIAN_CURRENCY
import java.util.*

class PurchaseAdapter(
    private val onPurchaseClickListener: OnPurchaseClickListener,
) : RecyclerView.Adapter<PurchaseAdapter.ViewHolder>() {

    private var items: LinkedList<FullPurchase> = LinkedList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val purchaseRecycleView = items[position]

        holder.apply {
            name.text = purchaseRecycleView.purchase.name
            price.text = purchaseRecycleView.purchase.price.toString() + RUSSIAN_CURRENCY
            category.text = purchaseRecycleView.category.name
            bindView(purchaseRecycleView.purchase, onPurchaseClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_info_purchase, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.TV_name_recycler_purchase)
        val price: TextView = view.findViewById(R.id.TV_price_purchase_in_recycler)
        val menu: TextView = view.findViewById(R.id.TV_purchase_menu)
        val category: TextView = view.findViewById(R.id.TV_category_purchase_in_recycler)

        fun bindView(purchase: Purchase, onPurchaseClickListener: OnPurchaseClickListener) {
            name.setOnClickListener { onPurchaseClickListener.clickedPurchaseItem(purchase) }
            price.setOnClickListener { onPurchaseClickListener.clickedPurchaseItem(purchase) }

            menu.setOnClickListener {
                val popup = PopupMenu(it.context, menu)
                popup.inflate(R.menu.options_menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_delete -> {
                            onPurchaseClickListener.deleteItem(purchase)
                        }

                        R.id.menu_edit -> {
                            onPurchaseClickListener.editItem(purchase)
                        }
                    }

                    false
                }

                popup.show()
            }
        }
    }

    fun updateItems(items: LinkedList<FullPurchase>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun removePurchase(id: Long?) {
        val purchaseToRemove = items.find { it.purchase.id == id }
        items.remove(purchaseToRemove)
        notifyDataSetChanged()
    }
}
