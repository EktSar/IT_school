package com.example.ekaterina_sarycheva_shop.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ekaterina_sarycheva_shop.entitins.Category

@Database(entities = [Category::class], version = 1) // обновление версии: android room migrations
abstract  class ShopDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
}