/**
 * Course: MAD302-01 Android Development — Final Project
 * Student Name: Darshilkumar Karkar
 * Student ID: A00203357
 * Date: 22 April 2026.
 * Description: Screen for adding a new product or editing an existing one.
 *              Handles form validation and data retrieval from Intent.
 */
package com.example.productmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for adding or editing product details.
 * It validates inputs and simulates saving data.
 */
class AddEditProductActivity : AppCompatActivity() {

    private var existingProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_product)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val etName = findViewById<EditText>(R.id.etName)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Retrieve product if we're in Edit mode
        existingProduct = intent.getSerializableExtra("product") as? Product

        if (existingProduct != null) {
            title = "Edit Product"
            etName.setText(existingProduct!!.name)
            etPrice.setText(existingProduct!!.price.toString())
            etDescription.setText(existingProduct!!.description)
        } else {
            title = "Add Product"
        }

        btnSave.setOnClickListener {
            saveProduct(etName.text.toString(), etPrice.text.toString(), etDescription.text.toString())
        }
    }

    /**
     * Validates and saves the product data.
     *
     * @param name The name of the product.
     * @param priceText The price of the product as a string.
     * @param description The description of the product.
     */
    private fun saveProduct(name: String, priceText: String, description: String) {
        val trimmedName = name.trim()
        val trimmedPrice = priceText.trim()
        val trimmedDescription = description.trim()

        if (trimmedName.isEmpty() || trimmedPrice.isEmpty() || trimmedDescription.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val price = trimmedPrice.toDoubleOrNull()
        if (price == null || price <= 0) {
            Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
            return
        }

        // In a real app, save to database here.
        val resultMessage = if (existingProduct == null) "Product added" else "Product updated"
        Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}