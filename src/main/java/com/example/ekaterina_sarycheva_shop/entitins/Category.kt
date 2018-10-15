package com.example.ekaterina_sarycheva_shop.entitins

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import kotlinx.serialization.Serializable

// Категория
@Entity(tableName = "categories") // Таблица с названием
@Serializable
data class Category( // data - будет сравнение по параметрам
        @PrimaryKey val id: Int, // id один в своем роде
        val imageUrl: String,
        val title: String,
        val price: String,
        val description: String,
        val url: String
        //val rating: Double,
        //val productVCount: Int
)
