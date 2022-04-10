package com.tarasovms.billing.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.persAssistant.shopping_list.R

// TODO сделать xml файл для реесайкла
class ProductAdapter(private var products: List<SkuDetails>, param: (SkuDetails) -> Unit) :
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

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


            fun bindView( product: SkuDetails) {

            }
        }

        fun updateItems(products: List<SkuDetails>) {
            this.products = products
            notifyDataSetChanged()
        }

}