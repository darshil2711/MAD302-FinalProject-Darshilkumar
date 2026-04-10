/**
 * Course: MAD302-01 Android Development — Final Project
 * Student Name: Darshilkumar Karkar
 * Student ID: A00203357
 * Date: 22 April 2026.
 * Description: Main screen showing all products in a RecyclerView.
 *              Handles navigation to Add and Detail screens.
 */
package com.example.productmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

/**
 * The entry point of the app. It manages the product list and 
 * handles the core navigation flow.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    
    // We now use the shared repository instead of a local list
    private val products = ProductRepository.getAllProducts()

    /**
     * Toggles between the list and the "no data" message so the user 
     * isn't staring at a blank screen if the inventory is empty.
     */
    private fun updateEmptyState() {
        val emptyStateContainer = findViewById<View>(R.id.tvEmpty)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        
        if (products.isEmpty()) {
            emptyStateContainer.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyStateContainer.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    /** 
     * We refresh here to ensure any changes made in the Add/Edit 
     * activities are immediately visible when the user hits 'Back'.
     */
    override fun onResume() {
        super.onResume()
        // Force the adapter to refresh with the latest data from the repo
        adapter.notifyDataSetChanged()
        updateEmptyState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Product Manager"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(products) { product ->
            // Pass the selected product object to the detail screen
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        findViewById<ExtendedFloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, AddEditProductActivity::class.java))
        }

        updateEmptyState()
    }
}