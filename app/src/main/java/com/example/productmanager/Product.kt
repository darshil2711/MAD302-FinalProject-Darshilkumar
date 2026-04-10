/**
 * Course: MAD302-01 Android Development — Final Project
 * Student Name: Darshilkumar Karkar
 * Student ID: A00203357
 * Date: 22 April 2026.
 * Description: Data model representing a Product in the inventory system.
 */

package com.example.productmanager

import java.io.Serializable

/**
 * Data class representing a product in the inventory.
 *
 * @param id Unique identifier for the product.
 * @param name Display name of the product.
 * @param price Price of the product in dollars.
 * @param description A short description of the product.
 */
data class Product(
    val id: Int,
    var name: String,
    var price: Double,
    var description: String
) : Serializable // Allows passing via Intent