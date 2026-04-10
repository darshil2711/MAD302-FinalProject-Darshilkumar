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

/**
 * Adapter class to bridge our product list data with the RecyclerView UI.
 */
class ProductAdapter(
    private val products: MutableList<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    /**
     * ViewHolder acts as a container for each item in the list.
     * It keeps references to the views so we don't have to call findViewById repeatedly.
     */
    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvDescription: TextView = view.findViewById(R.id.tvProductDescriptionShort)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Inflate the custom card layout we designed for each product
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        
        // Setting up the data for this specific row
        holder.tvName.text = product.name
        holder.tvPrice.text = "$${String.format("%.2f", product.price)}"
        holder.tvDescription.text = product.description
        
        // Send the product back to the activity when the card is clicked
        holder.itemView.setOnClickListener { onClick(product) }
    }

    override fun getItemCount() = products.size

    /**
     * Helper method to remove a product and update the UI accordingly.
     */
    fun removeAt(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
    }
}