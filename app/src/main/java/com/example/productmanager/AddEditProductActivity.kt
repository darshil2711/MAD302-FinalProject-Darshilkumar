package com.example.productmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Screen for adding a new product or editing an existing one.
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
            val name = etName.text.toString().trim()
            val priceText = etPrice.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (name.isEmpty() || priceText.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceText.toDoubleOrNull()
            if (price == null || price <= 0) {
                Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // In a real app, save to database here.
            val resultMessage = if (existingProduct == null) "Product added" else "Product updated"
            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}