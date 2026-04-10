/**
 * Course: MAD302-01 Android Development — Final Project
 * Student Name: Darshilkumar Karkar
 * Student ID: A00203357
 * Date: 22 April 2026.
 * Description: Singleton repository to manage the product list in memory.
 */
package com.example.productmanager

/**
 * A simple singleton to hold our data. 
 * Since we aren't using a database yet, this allows all activities
 * to see and modify the same list of products.
 */
object ProductRepository {
    private val products = mutableListOf(
        Product(1, "MacBook Pro", 2499.00, "14-inch M3 chip, 16GB RAM, 512GB SSD. Perfect for developers."),
        Product(2, "iPhone 15 Pro", 1199.00, "Natural Titanium, 256GB storage, A17 Pro chip for gaming."),
        Product(3, "Sony WH-1000XM5", 349.99, "Industry-leading noise cancellation and crystal clear audio."),
        Product(4, "Samsung S24 Ultra", 1299.00, "AI-powered camera features, 200MP lens, Titanium build."),
        Product(5, "iPad Air", 599.00, "10.9-inch liquid retina display, M2 chip, supports Apple Pencil.")
    )

    fun getAllProducts(): MutableList<Product> = products

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun updateProduct(updatedProduct: Product) {
        val index = products.indexOfFirst { it.id == updatedProduct.id }
        if (index != -1) {
            products[index] = updatedProduct
        }
    }

    fun deleteProduct(product: Product) {
        products.removeAll { it.id == product.id }
    }
}