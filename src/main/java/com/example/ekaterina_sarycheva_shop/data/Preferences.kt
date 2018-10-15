package com.example.ekaterina_sarycheva_shop.data

import com.chibatching.kotpref.KotprefModel

// Настройка: сохранение текущего экрана при выходе из приложения
object Preferences : KotprefModel(){
    var currentCategoryUrl by nullableStringPref(default = null) // Доп.действие, делегация, обслуживается
    var isLoggedIn by booleanPref(default = false) // РЕАЛИЗОВАТЬ ЛОГИКУ!!! Минуя входной экран
}

