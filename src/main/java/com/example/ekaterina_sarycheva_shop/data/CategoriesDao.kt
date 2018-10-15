package com.example.ekaterina_sarycheva_shop.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ekaterina_sarycheva_shop.entitins.Category

@Dao
interface  CategoriesDao{

    @Insert (onConflict = OnConflictStrategy.REPLACE) // Добавление объектов в базу
    fun add(category: Category)

    @Query("SELECT * FROM categories") // Запрос на получение данных
    fun all(): List<Category>

    //@Query("SELECT * FROM categories WHERE id=:id")
    //fun byId(id: Int): Category

    //@Query("SELECT * FROM categories WHERE rating >= 3.0 AND productVCount > 0")
    // fun notEmptyWithHighRating(): List<Category>

}
