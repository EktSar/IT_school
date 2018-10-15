package com.example.ekaterina_sarycheva_shop.View

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.appcompat.v7.toolbar
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.drawerLayout

class TestActivity : AppCompatActivity(){
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawerLayout = drawerLayout {
            verticalLayout {
                toolbar = toolbar {
                    backgroundColor = Color.parseColor("#3F51B5")
                    background.alpha = 140 // Прозрачность
                    //Typeface.createFromAsset(context.assets, "anti.ttf")
                    title = "ЖАНРЫ"
                    //setTitleTextColor(0x000000)
                }.lparams {
                    width = matchParent
                    height = dip(45)
                }
                button {
                    onClick {
                        startActivity<CategoriesActivity>()
                    }
                }
            }
            // MENU MENU MENU MENU MENU MENU MENU MENU MENU MENU MENU MENU MENU
            navigationView {
                backgroundColor = Color.parseColor("#3F51B5")
                background.alpha = 140 // Прозрачность
                recyclerView {
                    layoutManager = LinearLayoutManager(this@TestActivity)
                    //adapter = categoriesAdapter
                }
            }.lparams {
                gravity = Gravity.START
                height = matchParent
            }
            //openDrawer(Gravity.START)
        }

        setSupportActionBar(toolbar) // замена ActionBar

        drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) {

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
