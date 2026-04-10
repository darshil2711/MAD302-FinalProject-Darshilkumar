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
            val name = etName.text.toString().trim()
            val priceText = etPrice.text.toString().trim()
            val description = etDescription.text.toString().trim()
            
            saveProduct(name, priceText, description)
        }
    }

    private fun saveProduct(name: String, priceText: String, description: String) {
        if (name.isEmpty() || priceText.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceText.toDoubleOrNull()
        if (price == null || price <= 0) {
            Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
            return
        }

        if (existingProduct == null) {
            // Generate a simple unique ID and add to our shared repository
            val newId = (ProductRepository.getAllProducts().maxOfOrNull { it.id } ?: 0) + 1
            val newProduct = Product(newId, name, price, description)
            ProductRepository.addProduct(newProduct)
            Toast.makeText(this, "Product added successfully!", Toast.LENGTH_SHORT).show()
        } else {
            // Update the existing product in the repo
            existingProduct!!.name = name
            existingProduct!!.price = price
            existingProduct!!.description = description
            ProductRepository.updateProduct(existingProduct!!)
            Toast.makeText(this, "Changes saved!", Toast.LENGTH_SHORT).show()
        }
        
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}