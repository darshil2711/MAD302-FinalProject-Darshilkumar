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
 * Data class for our products. 
 * We implement Serializable so we can easily pass product objects between 
 * activities using Intents.
 */
data class Product(
    val id: Int,
    var name: String,
    var price: Double,
    var description: String
) : Serializable