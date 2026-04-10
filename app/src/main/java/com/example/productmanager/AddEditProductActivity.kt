package com.example.productmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Handles both adding a new product and editing an existing one.
 * If a product is passed via Intent, the form is pre-filled for editing.
 * Otherwise, a blank form is shown for adding a new product.
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

        // Check if editing an existing product
        existingProduct = intent.getSerializableExtra("product") as? Product

        if (existingProduct != null) {
            // Pre-fill form with existing values
            title = "Edit Product"
            etName.setText(existingProduct!!.name)
            etPrice.setText(existingProduct!!.price.toString())
            etDescription.setText(existingProduct!!.description)
        } else {
            title = "Add Product"
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveProduct(etName, etPrice, etDescription)
        }
    }

    /**
     * Validates form inputs and saves (or updates) the product.
     *
     * @param etName EditText containing the product name.
     * @param etPrice EditText containing the product price.
     * @param etDescription EditText containing the product description.
     */
    private fun saveProduct(
        etName: EditText,
        etPrice: EditText,
        etDescription: EditText
    ) {
        val name = etName.text.toString().trim()
        val priceText = etPrice.text.toString().trim()
        val description = etDescription.text.toString().trim()

        // Validate — all fields must be filled
        if (name.isEmpty() || priceText.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceText.toDoubleOrNull()
        if (price == null || price <= 0) {
            Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
            return
        }

        // In a full implementation, save to database here
        val msg = if (existingProduct != null) "Product updated!" else "Product added!"
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        finish() // Return to previous screen
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}