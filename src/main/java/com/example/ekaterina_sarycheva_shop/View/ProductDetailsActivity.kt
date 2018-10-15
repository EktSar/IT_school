package com.example.ekaterina_sarycheva_shop.View

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.ekaterina_sarycheva_shop.R
import com.example.ekaterina_sarycheva_shop.View.java.ViewPagerAdapter
import com.example.ekaterina_sarycheva_shop.entitins.Product
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.support.v4.nestedScrollView

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = intent.getStringExtra("product")
        val product: Product = JSON.parse(json) // Из строки получаем объект


        val drawerLayout = drawerLayout {
            nestedScrollView {
                verticalLayout {
                    backgroundResource = R.drawable.bg

                    toolBar = toolbar {
                        backgroundColor = Color.parseColor("#3F51B5")
                        background.alpha = 140 // Прозрачность
                        //Typeface.createFromAsset(context.assets, "anti.ttf")
                        //setTitleTextColor(0x000000)
                    }.lparams {
                        width = matchParent
                        height = dip(45)
                    }

                    //val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
                    //val viewPager = findViewById<ViewPager>(R.id.view_pager)
                    //val adapter = ViewPagerAdapter()
                    //viewPager.adapter = adapter
                    //wormDotsIndicator.setViewPager(viewPager)

                    textView {
                        text = product.description
                        textSize = 26f
                    }

                    button {
                        text = "Заказать"
                        textSize = 29f
                        typeface = Typeface.createFromAsset(assets, "intro_inline.otf")
                        backgroundColor = Color.parseColor("#D9EAF0")
                        background.alpha = 55 // Прозрачность (от 0 до 255)
                        onClick{
                            startActivity<OrderActivity>()
                        }
                    }.lparams{
                        width = matchParent
                        height = dip(60)
                        horizontalMargin = dip(10)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                }
            }
                navigationView {
                    backgroundColor = Color.WHITE
                    recyclerView {
                        layoutManager = LinearLayoutManager(this@ProductDetailsActivity)
                    }
                }.lparams {
                    gravity = Gravity.START
                    height = matchParent
                }
                //openDrawer(Gravity.START)
            }

            setSupportActionBar(toolBar) // замена ActionBar

            drawerToggle = object : ActionBarDrawerToggle(this@ProductDetailsActivity, drawerLayout, toolBar, 0, 0) {

                override fun onDrawerClosed(view: View) {
                    supportInvalidateOptionsMenu()
                    //drawerOpened = false;
                }

                override fun onDrawerOpened(drawerView: View) {
                    supportInvalidateOptionsMenu()
                    //drawerOpened = true;
                }
            }
            drawerToggle.isDrawerIndicatorEnabled = true
            drawerLayout.setDrawerListener(drawerToggle)
            drawerToggle.syncState()
        }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }
}


// КНОПКА ТВИТЕР
//button {
//    text = "Поделиться"
//    onClick{
//        val intent = Intent()
//                .setData(Uri.parse("http://twitter.com/share?text=$"))
//                .setAction(Intent.ACTION_VIEW)
//            startActivity(intent)
//    }