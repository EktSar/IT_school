package com.example.ekaterina_sarycheva_shop

import android.app.Application
import android.arch.persistence.room.Room
import com.chibatching.kotpref.Kotpref
import com.example.ekaterina_sarycheva_shop.data.ShopDatabase

// Класс, который является приложение, при котором
// будут выполнены действия: инициализация kotpref
// application является контекстом
// Класс App будет классом нашего приложения, в манифесте указываем!

lateinit var db: ShopDatabase // Без значения lateinit, вызов базы данных из любого места программа

class App : Application(){

    override fun onCreate(){
        super.onCreate()
        Kotpref.init(this)

        db = Room.databaseBuilder( // Контекст андроида, схема бд
                applicationContext,
                ShopDatabase::class.java,
                "shop"
        ).build()
    }
}