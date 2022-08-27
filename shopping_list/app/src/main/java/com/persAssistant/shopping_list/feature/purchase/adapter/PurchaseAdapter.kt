package com.persAssistant.shopping_list.feature.purchase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.databinding.ItemInfoPurchaseBinding
import com.persAssistant.shopping_list.domain.entities.FullPurchase
import com.persAssistant.shopping_list.domain.entities.Purchase
import com.persAssistant.shopping_list.util.DiffUtils
import com.persAssistant.shopping_list.common.RUSSIAN_CURRENCY
import java.util.*
import kotlin.properties.Delegates

class PurchaseAdapter(
    private val onPurchaseClickListener: OnPurchaseClickListener,
) : RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>(), DiffUtils {

    private var items: LinkedList<FullPurchase> by Delegates.observable(LinkedList()) { _, old, new ->
        autoNotify(old, new) { oldItem, newItem ->
            oldItem.purchase.id == newItem.purchase.id
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchaseRecycleView = items[position]

        holder.apply {
            name.text = purchaseRecycleView.purchase.name
            price.text = priceWithConsiderationOfQuantity(
                binding.root.context,
                purchaseRecycleView.purchase
            )
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
            onPurchaseClickListener: OnPurchaseClickListener,
        ) {
            name.setOnClickListener { onPurchaseClickListener.clickedPurchaseItem(purchase) }
            price.setOnClickListener { onPurchaseClickListener.clickedPurchaseItem(purchase) }

            menu.setOnClickListener {
                initPopMenu(
                    context = it.context,
                    menu = menu,
                    onPurchaseClickListener = onPurchaseClickListener,
                    purchase = purchase,
                )
            }
        }

        private fun initPopMenu(
            context: Context,
            menu: View,
            onPurchaseClickListener: OnPurchaseClickListener,
            purchase: Purchase,
        ) {
            val popup = PopupMenu(context, menu)
            popup.run {
                inflate(R.menu.options_menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_delete -> onPurchaseClickListener.deleteItem(purchase)
                        R.id.menu_edit -> onPurchaseClickListener.editItem(purchase)
                    }

                    false
                }

                show()
            }
        }
    }

    fun updateItems(newList: LinkedList<FullPurchase>) {
        this.items = newList
    }

    private fun priceWithConsiderationOfQuantity(context: Context, purchase: Purchase): String {
        val pricePurchase = purchase.price
        val quantity = purchase.quantity
        val result = pricePurchase.toFloat().times((quantity.toFloat()))

        return if (quantity.toFloat() <= ONE_FLOAT) context.getString(
            R.string.composite_of_two,
            pricePurchase.toString(),
            RUSSIAN_CURRENCY,
        )
        else context.getString(
            R.string.composite_of_three_with_brackets_and_cost_of_one,
            result.toString(),
            RUSSIAN_CURRENCY,
            pricePurchase.toString(),
            quantity,
        )
    }

    companion object {
        private const val ONE_FLOAT = 1f
    }
}
