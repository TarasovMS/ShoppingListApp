package com.persAssistant.shopping_list.feature.billing.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.persAssistant.shopping_list.R

// TODO сделать xml файл для реесайкла
class ProductAdapter(private var products: List<SkuDetails>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.apply {
            bindView(product)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_info_category, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_recycler_info_category_name_tv)
        val price: TextView = view.findViewById(R.id.item_recycler_info_category_price_tv)

        fun bindView(product: SkuDetails) {
            name.text = product.description
            price.text = product.price
        }
    }

    fun updateItems(products: List<SkuDetails>) {
        this.products = products
        notifyDataSetChanged()
    }

}
