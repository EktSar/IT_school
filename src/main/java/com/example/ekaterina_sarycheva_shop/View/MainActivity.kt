package com.example.ekaterina_sarycheva_shop.View

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import com.example.ekaterina_sarycheva_shop.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView {
            layoutManager = GridLayoutManager(this@MainActivity,2)
        }
        //Log.i("MainActivity", "Hello")

        frameLayout{
            backgroundResource = R.drawable.bg

            // ЛОГО
            imageView {
               imageResource = R.drawable.icon
            }.lparams {
                width = dip(100)
                height = dip(100)
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                topMargin = dip(50)
            }

            // ПОЛЯ С ВВОДОМ
            verticalLayout {
                editText {
                    hint = "Email или телефон"
                }

                editText {
                    hint = "Пароль"
                }

                button {
                    text = "Вход"
                    textSize = 29f
                    typeface = Typeface.createFromAsset(assets, "intro_inline.otf")
                    backgroundColor = Color.parseColor("#D9EAF0")
                    background.alpha = 55 // Прозрачность (от 0 до 255)
                    onClick {
                        startActivity<CategoriesActivity>()
                    }
                }.lparams {
                    width = matchParent
                    height = dip(60)
                    topMargin = dip(20)
                    gravity = Gravity.CENTER_HORIZONTAL

                }
            }.lparams {
                width = matchParent
                gravity = Gravity.CENTER
                horizontalMargin = dip(10)
            }

            // КНОПКА ЗАРЕГИСТРИРОВАТЬСЯ
            button {
                backgroundColor = Color.TRANSPARENT
                text = "Зарегистрироваться"
                textSize = 18f
                onClick{
                    //startActivity<TestActivity>()
                    startActivity<Registration>()
                }
            }.lparams{
                width = matchParent
                height = dip(60)
                bottomMargin = dip(20)
                gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            }
        }

    }
}