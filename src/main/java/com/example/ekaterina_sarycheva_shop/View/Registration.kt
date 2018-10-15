package com.example.ekaterina_sarycheva_shop.View

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import com.example.ekaterina_sarycheva_shop.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.sdk27.coroutines.onClick


class Registration : AppCompatActivity() {

    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {
            verticalLayout {
                toolBar = toolbar {
                    backgroundColor = Color.parseColor("#3F51B5")
                    background.alpha = 140 // Прозрачность
                    //Typeface.createFromAsset(context.assets, "anti.ttf")
                    title = "РЕГИСТРАЦИЯ"
                    //setTitleTextColor(0x000000)
                }.lparams {
                    width = matchParent
                    height = dip(45)
                }

                verticalLayout {

                    editText {
                        hint = "Email или телефон"
                    }
                    editText {
                        hint = "Имя"
                    }
                    editText {
                        hint = "Фамилия"
                    }
                    editText {
                        hint = "Пароль"
                    }

                    button {
                        text = "Cоздать"
                        textSize = 27f
                        typeface = Typeface.createFromAsset(assets, "intro_inline.otf")
                        backgroundColor = Color.parseColor("#D9EAF0")
                        background.alpha = 55 // Прозрачность (от 0 до 255)
                        onClick {
                            startActivity<MainActivity>()
                        }
                    }.lparams {
                        width = matchParent
                        height = dip(60)
                        topMargin = dip(20)
                    }

                    textView {
                        text = "Мир картин ждет тебя! "
                        textSize = 28f
                        textColor = Color.BLACK
                        typeface = Typeface.createFromAsset(assets, "chan.ttf")
                    }.lparams {
                        topMargin = dip(10)
                        gravity = Gravity.CENTER
                    }

                }.lparams {
                    width = matchParent
                    horizontalMargin = dip(20)
                    topMargin = dip(10)
                }
            }

            // ЛОГО
            verticalLayout {
                imageView {
                    imageResource = R.drawable.icon
                }.lparams {
                    width = dip(50)
                    height = dip(50)
                }

                textView {
                    text = "PicCat"
                    typeface = Typeface.createFromAsset(context.assets, "arial.ttf")
                    typeface.isBold
                    textSize = 15f
                }
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                bottomMargin = dip(30)
            }
        }
    }
}
