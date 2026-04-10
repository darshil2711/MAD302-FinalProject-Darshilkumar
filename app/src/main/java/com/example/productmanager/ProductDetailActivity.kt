/**
 * Course: MAD302-01 Android Development — Final Project
 * Student Name: Darshilkumar Karkar
 * Student ID: A00203357
 * Date: 22 April 2026.
 * Description: Displays full details for a single product.
 *              Receives product data via Intent and provides Edit and Delete action buttons.
 */
package com.example.productmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
 * Displays full details for a single product.
 * Receives product data via Intent and provides
 * Edit and Delete action buttons.
 */
class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        title = "Product Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Back button

        // Retrieve product passed from MainActivity
        val product = intent.getSerializableExtra("product") as Product

        // Populate UI fields
        findViewById<TextView>(R.id.tvDetailName).text = product.name
        findViewById<TextView>(R.id.tvDetailPrice).text =
            "$${String.format("%.2f", product.price)}"
        findViewById<TextView>(R.id.tvDetailDescription).text = product.description

        // Open edit form with this product pre-loaded
        findViewById<Button>(R.id.btnEdit).setOnClickListener {
            val intent = Intent(this, AddEditProductActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }

        // Show confirmation dialog before deleting
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            showDeleteConfirmation(product)
        }
    }

    /**
     * Shows an AlertDialog asking the user to confirm product deletion.
     * @param product The product to be deleted upon confirmation.
     */
    private fun showDeleteConfirmation(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete '${product.name}'?")
            .setPositiveButton("Delete") { _, _ ->
                // Return to list (in full implementation, remove from data source)
                Toast.makeText(this, "${product.name} deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("Cancel", null) // Dismiss dialog
            .show()
    }

    /** Handles the Up/Back navigation button in the action bar. */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}