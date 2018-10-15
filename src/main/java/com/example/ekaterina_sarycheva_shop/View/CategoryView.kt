package com.example.ekaterina_sarycheva_shop.View

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class CategoryView(context: Context) : FrameLayout(context) {
    lateinit var pictureView: ImageView
    lateinit var titleView: TextView
    lateinit var priceView: TextView

    init { // код, который выполнится при создании каждого объекта ProductView

        // Описание интерфейса ячейки
        frameLayout {
            verticalLayout {
                cardView {
                    radius = 10f
                    elevation = 2.5f // Подчеркивает

                    verticalLayout {
                        pictureView = imageView {

                        }.lparams {
                            height = dip(200)
                            width = matchParent
                            topMargin = dip(5)
                        }

                        titleView = textView {
                            typeface = Typeface.createFromAsset(context.assets, "arial.ttf")
                            typeface.isBold
                            gravity = Gravity.CENTER
                        }

                        priceView = textView {
                            typeface = Typeface.createFromAsset(context.assets, "arial.ttf")
                            gravity = Gravity.CENTER
                        }
                    }
                }.lparams {
                    horizontalMargin = dip(10)
                    topMargin = dip(10)
                    bottomMargin = dip(10)
                    height = dip(248)
                    width = matchParent
                    gravity = Gravity.CENTER
                }
            }
        }
    }
}