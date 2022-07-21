package com.persAssistant.shopping_list.ui.fragment.purchase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.databinding.ItemInfoPurchaseBinding
import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.util.RUSSIAN_CURRENCY
import java.util.*

class PurchaseAdapter(
    private val onPurchaseClickListener: OnPurchaseClickListener,
) : RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    private var items: LinkedList<FullPurchase> = LinkedList()

    override fun getItemCount(): Int { return items.size}

    //TODO Переделать 1)price.text =  юююю

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchaseRecycleView = items[position]

        holder.apply {
            name.text = purchaseRecycleView.purchase.name
            price.text = purchaseRecycleView.purchase.price.toString() + RUSSIAN_CURRENCY
            category.text = purchaseRecycleView.category.name
            bindView(purchaseRecycleView.purchase, onPurchaseClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder(
            ItemInfoPurchaseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class PurchaseViewHolder(
        val binding: ItemInfoPurchaseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.itemRecyclerInfoPurchaseNameTv
        val price = binding.itemRecyclerInfoPurchasePriceTv
        val menu = binding.itemRecyclerInfoPurchaseMenuTv
        val category = binding.itemRecyclerInfoCategoryInPurchaseTv

        fun bindView(
            purchase: Purchase,
            onPurchaseClickListener: OnPurchaseClickListener
        ) {
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
