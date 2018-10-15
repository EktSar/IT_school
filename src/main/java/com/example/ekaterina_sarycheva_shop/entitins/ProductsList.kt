package com.example.ekaterina_sarycheva_shop.entitins

import kotlinx.serialization.Serializable

@Serializable
class ProductsList (
    val products: List<Product>
)