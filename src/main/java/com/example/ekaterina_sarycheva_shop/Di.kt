package com.example.ekaterina_sarycheva_shop

import com.example.ekaterina_sarycheva_shop.data.OkHttpRequestMaker
import com.example.ekaterina_sarycheva_shop.data.RequestMaker
import com.example.ekaterina_sarycheva_shop.presentation.CategoriesPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val di = Kodein{
    bind<RequestMaker>() with singleton { OkHttpRequestMaker() }
    // Здесь можно сменить принцип из сделанного класса (чистая архитектура)
    // тогда по всей программе он изменится
    bind<CategoriesPresenter>() with singleton { CategoriesPresenter(instance()) }
    // Когда кто-то запрашивает CategoriesPresenter, мы отдаем singleton(одно на всё приложение),
    // требуется RequestMaker, он уже есть в di, поэтому используем функцию instance
}