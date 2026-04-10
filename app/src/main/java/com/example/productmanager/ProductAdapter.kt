/**
 * Course: MAD302-01 Android Development — Final Project
 * Student Name: Darshilkumar Karkar
 * Student ID: A00203357
 * Date: 22 April 2026.
 * Description: RecyclerView adapter for displaying the list of products.
 */
package com.example.productmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productmanager.Product

/**
 * RecyclerView adapter for displaying the list of products.
 *
 * @param products Mutable list of Product objects to display.
 * @param onClick Lambda called when a product card is tapped.
 */
class ProductAdapter(
    private val products: MutableList<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    /**
     * ViewHolder holding references to item views.
     * @param view The inflated CardView item layout.
     */
    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
    }

    /**
     * Inflates the item layout and creates a new ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    /** Binds product data to the ViewHolder views. */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.tvName.text = product.name
        holder.tvPrice.text = "$${String.format("%.2f", product.price)}"
        // Navigate to detail on click
        holder.itemView.setOnClickListener { onClick(product) }
    }

    /** Returns the total number of products in the list. */
    override fun getItemCount() = products.size

    /**
     * Removes a product at the given position and refreshes the list.
     * @param position Index of the product to remove.
     */
    fun removeAt(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
    }
}