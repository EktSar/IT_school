package com.example.ekaterina_sarycheva_shop.entitins

import android.icu.util.ULocale
import kotlinx.serialization.Serializable

// Список категорий
@Serializable
class CategoriesList(
        val categories: List<Category>
)