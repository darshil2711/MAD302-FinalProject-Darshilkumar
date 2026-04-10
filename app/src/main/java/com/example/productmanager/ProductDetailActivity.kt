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

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        
        // Enabling the back button in the top bar for easy navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Product Details"

        // Grab the product object we passed from the list screen
        val product = intent.getSerializableExtra("product") as Product

        findViewById<TextView>(R.id.tvDetailName).text = product.name
        findViewById<TextView>(R.id.tvDetailPrice).text =
            "$${String.format("%.2f", product.price)}"
        findViewById<TextView>(R.id.tvDetailDescription).text = product.description

        findViewById<Button>(R.id.btnEdit).setOnClickListener {
            // Re-use the Add screen for editing by passing the existing product
            val intent = Intent(this, AddEditProductActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            showDeleteConfirmation(product)
        }
    }

    private fun showDeleteConfirmation(product: Product) {
        // Standard confirmation dialog to prevent accidental deletions
        AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete '${product.name}'?")
            .setPositiveButton("Delete") { _, _ ->
                // Remove the product from our shared repository
                ProductRepository.deleteProduct(product)
                Toast.makeText(this, "${product.name} removed from inventory", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("Cancel", null) 
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}