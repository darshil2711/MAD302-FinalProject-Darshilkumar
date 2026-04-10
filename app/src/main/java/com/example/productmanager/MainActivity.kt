package com.example.productmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.productmanager.Product
import kotlin.jvm.java

/**
 * Course: MAD302-01 Android Development — Final Project
 * Student Name: Darshilkumar Karkar
 * Student ID: A00203357
 * Date: 22 April 2026.
 * Description: Main screen showing all products in a RecyclerView.
 *              Handles navigation to Add and Detail screens.
 */
/**
 * Main screen showing all products in a RecyclerView.
 * Handles navigation to Add and Detail screens.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    // Sample data — in a real app this would come from a database
    private val products = mutableListOf(
        Product(1, "Laptop", 999.99, "High-performance laptop"),
        Product(2, "Phone", 599.99, "Latest smartphone"),
        Product(3, "Headphones", 149.99, "Noise-cancelling headphones")
    )

    /**
     * Shows or hides the empty-state message depending on list size.
     */
    private fun updateEmptyState() {
        val tvEmpty = findViewById<TextView>(R.id.tvEmpty)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        if (products.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    // Call updateEmptyState() at end of onCreate()
// and override onResume to refresh after returning from Add/Edit
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        updateEmptyState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Product Manager"

        // Set up RecyclerView with LinearLayoutManager
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(products) { product ->
            // Open detail screen, pass product via Intent
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // FAB opens Add Product form
        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, AddEditProductActivity::class.java))
        }
    }
}