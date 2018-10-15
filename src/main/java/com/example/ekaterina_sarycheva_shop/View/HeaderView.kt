package com.example.ekaterina_sarycheva_shop.View

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.DialogTitle
import android.widget.FrameLayout
import android.widget.TextView
import org.jetbrains.anko.*

// Хедер
class HeaderView(context: Context) : FrameLayout(context){
    lateinit var titleView: TextView

    init {
        titleView = textView {
            typeface = Typeface.createFromAsset(context.assets, "anti.ttf")
            textSize = 20f
            padding = dip(10)
            backgroundColor = Color.parseColor("#0C00A2")
            textColor = Color.WHITE
            background.alpha = 200 // Прозрачность
            width = matchParent
        }
    }
}