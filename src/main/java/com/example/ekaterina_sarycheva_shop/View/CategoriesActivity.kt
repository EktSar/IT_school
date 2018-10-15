package com.example.ekaterina_sarycheva_shop.View

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.NavigationMenuView
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ekaterina_sarycheva_shop.*
import com.example.ekaterina_sarycheva_shop.R
import com.example.ekaterina_sarycheva_shop.data.Preferences
import com.example.ekaterina_sarycheva_shop.data.RequestMaker
import com.example.ekaterina_sarycheva_shop.entitins.CategoriesList
import com.example.ekaterina_sarycheva_shop.entitins.Category
import com.example.ekaterina_sarycheva_shop.presentation.CategoriesPresenter
import com.example.ekaterina_sarycheva_shop.presentation.CategoriesView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.design._BottomNavigationView
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.drawerLayout
import org.kodein.di.direct
import org.kodein.di.generic.instance

class CategoriesActivity : CategoriesView, AppCompatActivity() {

    // Запрос к json через класс
    // private  val requestMaker: RequestMaker = di.direct.instance()

    private val presenter: CategoriesPresenter = di.direct.instance() // получить напрямую из di объект категории презентора

    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var loaderView: ProgressBar
    private lateinit var contentView: LinearLayout

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolBar: Toolbar
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        //if (Preferences.currentCategoryUrl != null){
        //    startActivity<ProductsActivity>("url" to Preferences.currentCategoryUrl)
        //}
        // Переключение потоков
        //GlobalScope.launch(Dispatchers.Main) {

        categoriesAdapter = CategoriesAdapter(this)

        val drawerLayout = drawerLayout {
            val frameLayout = frameLayout {
                loaderView = progressBar {
                    visibility = View.GONE// кружочек
                }.lparams {
                    gravity = Gravity.CENTER
                }

                contentView = verticalLayout {
                    visibility = View.GONE

                    toolBar = toolbar {
                        backgroundColor = Color.parseColor("#3F51B5")
                        background.alpha = 140 // Прозрачность
                        //Typeface.createFromAsset(context.assets, "anti.ttf")
                        title = "ЖАНРЫ"
                        //setTitleTextColor(0x000000)
                    }.lparams {
                        width = matchParent
                        height = dip(45)
                    }

                    // ЗАДНИЙ ПЛАН
                    backgroundResource = R.drawable.bg
                    // Отображение
                    textView {
                        text = "Нажми на понравившийся жанр! "
                        textSize = 24f
                        textColor = Color.BLACK
                        typeface = Typeface.create(Typeface.createFromAsset(assets, "chan.ttf"), Typeface.BOLD)
                    }.lparams {
                        topMargin = dip(10)
                        gravity = Gravity.CENTER
                    }

                    recyclerView {
                        layoutManager = GridLayoutManager(this@CategoriesActivity, 2)
                        adapter = categoriesAdapter
                    }
                }.lparams {
                    width = matchParent
                    height = matchParent
                }
            }

            setContentView(R.layout.activity_main)
            navigationView = findViewById(R.id.nav_view)
            //navigationView.setNavigationItemSelectedListener {
            //   fun onNavigationItemSelected(MenuItem-item)
            //}

            //navigationView {
            //backgroundColor = Color.parseColor("#3F51B5")
            //    background.alpha = 140 // Прозрачность
            //    recyclerView {
            //        layoutManager = LinearLayoutManager(this@CategoriesActivity)
            //        //adapter = orderAdapter
            //    }
            //}.lparams {
            //    gravity = Gravity.START
            //    height = matchParent
            //}
        }

        presenter.onCreateView(this@CategoriesActivity)
        setSupportActionBar(toolBar) // замена ActionBar

        drawerToggle = object : ActionBarDrawerToggle(this@CategoriesActivity, drawerLayout, toolBar, 0, 0) {

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

    // URL получаем из намерения, по которому был запущен экран
    //val url = intent.getStringExtra("url")

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    // при появлении экрана, onResume для андроида
    override fun onResume() {
        super.onResume()
        presenter.onAppear()
    }

    // при исчезновении экрана
    override fun onPause() {
        super.onPause()
        presenter.onDisappear()
    }

    override fun displayLoading() {
        loaderView.visibility = View.VISIBLE
        contentView.visibility = View.GONE
    }

    // CategoriesActivity отображает список категорий
    override fun displayCategories(categories: List<Category>) {
        loaderView.visibility = View.GONE
        contentView.visibility = View.VISIBLE
        categoriesAdapter.update(categories)
    }
}






