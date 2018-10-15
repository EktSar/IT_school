package com.example.ekaterina_sarycheva_shop.View

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.ekaterina_sarycheva_shop.*
import com.example.ekaterina_sarycheva_shop.data.RequestMaker
import com.example.ekaterina_sarycheva_shop.entitins.Category
import com.example.ekaterina_sarycheva_shop.entitins.Product
import com.example.ekaterina_sarycheva_shop.entitins.ProductsList
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.drawerLayout
import org.kodein.di.direct
import org.kodein.di.generic.instance

class ProductsActivity : AppCompatActivity() {

    val requestMaker: RequestMaker = di.direct.instance()

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Потоки, сколько угодно, кроме async, Dispatchers.Main - главный поток, отрисовка интерфейса, Dispatchers.IO - обращение к сети и отправка в сеть, ввод/вывод
        GlobalScope.launch(Dispatchers.Main) {

            // Кружочек
            frameLayout{
                progressBar {

                }.lparams{
                    gravity = Gravity.CENTER
                }
            }

            // Действие в другом потоке, запрос к сети, чтобы кружок не подвисал
            // набор потоков в сети IO

            val categoryJson = intent.getStringExtra("category")
            val category: Category = JSON.parse(categoryJson)

            val json = async(Dispatchers.IO) {
                requestMaker.make(category.url)
            }.await() // Дожидаемся, но потоки параллельны

            // Котлин с json
            val productsList: ProductsList = JSON.parse(json)

            val drawerLayout = drawerLayout {
                verticalLayout {
                    //customView<HeaderView> {
                    //    titleView.text = category.title
                    //}
                    toolBar = toolbar {
                        backgroundColor = Color.parseColor("#3F51B5")
                        background.alpha = 140 // Прозрачность
                        //Typeface.createFromAsset(context.assets, "anti.ttf")
                        title = category.title.toUpperCase()
                        //setTitleTextColor(0x000000)
                    }.lparams {
                        width = matchParent
                        height = dip(45)
                    }

                    // ЗАДНИЙ ПЛАН
                    backgroundResource = R.drawable.bg

                    textView {
                        text = "А теперь выбери технику исполнения"
                        textSize = 24f
                        textColor = Color.BLACK
                        typeface = Typeface.create(Typeface.createFromAsset(assets, "chan.ttf"), Typeface.BOLD)
                    }.lparams {
                        topMargin = dip(10)
                        bottomMargin = dip(5)
                        gravity = Gravity.CENTER
                    }

                    // Отображаем список
                    recyclerView {
                        layoutManager = GridLayoutManager(this@ProductsActivity, 2)
                        adapter = ProductsAdapter(products = productsList.products, context = this@ProductsActivity)
                    }
                }

                navigationView {
                    backgroundColor = Color.parseColor("#3F51B5")
                    background.alpha = 140 // Прозрачность
                    recyclerView {
                        layoutManager = LinearLayoutManager(this@ProductsActivity)
                        //adapter = categoriesAdapter
                    }
                }.lparams {
                    gravity = Gravity.START
                    height = matchParent
                }
            }
            setSupportActionBar(toolBar) // замена ActionBar

            drawerToggle = object : ActionBarDrawerToggle(this@ProductsActivity, drawerLayout, toolBar, 0, 0) {

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
    }
}



class ProductsAdapter(val products: List<Product>, val context: Context): RecyclerView.Adapter<ProductViewHolder>(){

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(recyclerView: ViewGroup, viewType: Int) = run{
        val itemView = ProductView(context)
        ProductViewHolder(view = itemView)
    }

    // Ячейка
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position] // Позиция продукта

        // Загрузка картинки
        Picasso.get().load(product.imageUrl).into(holder.view.pictureView)

        holder.view.titleView.text = product.title
        //holder.view.priceView.text = product.price //.toString()

        holder.view.onClick { // Ячейка, отображение и при клике вызывается окно с деталями продукта
            val json = JSON.nonstrict.stringify(product)
            context.startActivity<ProductDetailsActivity>(
                    "product" to json
            )
        }
    }
}
class ProductView(context: Context) : FrameLayout(context) {
    lateinit var titleView: TextView
    //lateinit var priceView: TextView
    lateinit var pictureView: ImageView

    init { // код, который выполнится при создании каждого объекта ProductView

        // Задаём параметры макета для ProductView.
        // То же самое, что и lparams
        layoutParams = LayoutParams(matchParent, wrapContent)

        // Описание интерфейса ячейки
        frameLayout {
            verticalLayout {
                cardView {
                    radius = 10f
                    elevation = 15f // Подчеркивает

                    //linearLayout {
                    // orientation = LinearLayout.VERTICAL

                    verticalLayout {
                        pictureView = imageView {

                        }.lparams {
                            height = dip(213)
                            gravity = Gravity.CENTER
                        }

                        titleView = textView {
                            typeface = Typeface.createFromAsset(context.assets, "arial.ttf")
                            typeface.isBold
                        }.lparams {
                            gravity = Gravity.CENTER
                            topMargin = dip(6)
                        }
                    }
                }.lparams {
                    margin = dip(10)
                    height = dip(246)
                    width = matchParent
                    gravity = Gravity.CENTER
                }
            }
        }
    }
}

class ProductViewHolder(val view: ProductView) : RecyclerView.ViewHolder(view)