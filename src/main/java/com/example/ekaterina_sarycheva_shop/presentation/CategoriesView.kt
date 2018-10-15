package com.example.ekaterina_sarycheva_shop.presentation

import com.example.ekaterina_sarycheva_shop.entitins.Category

//
interface CategoriesView {
    fun displayCategories(categories: List<Category>) // отображение категорий
    fun displayLoading() // отображение загрузки
}