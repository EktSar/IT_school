package com.example.ekaterina_sarycheva_shop.entitins

import kotlinx.serialization.Serializable

@Serializable
class Product(
        val id: Int,
        val title: String,
        val price: String,
        val description: String,
        val imageUrl: String
)
