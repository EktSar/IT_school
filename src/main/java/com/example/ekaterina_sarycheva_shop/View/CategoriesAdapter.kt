package com.example.ekaterina_sarycheva_shop.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.ekaterina_sarycheva_shop.data.Preferences
import com.example.ekaterina_sarycheva_shop.entitins.Category
import com.squareup.picasso.Picasso
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.UI
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textView

// Как они будут отображаться
class CategoriesAdapter(
        val context: Context
) : RecyclerView.Adapter<CategoryViewHolder>(){

    // Категория грузятся после появления активити
    var categories: List<Category> = emptyList()
    fun update(categories: List<Category>){
        this.categories = categories
        notifyDataSetChanged() // уведомляет адаптор, что список обновился и нужно его изменить
    }

    // Количество элементов в списке
    override fun getItemCount() = categories.size

    // Создание ячейки, рисование в графическом редакторе андроид
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = run {

        // Графический интерфейс
        val ui = context.UI{
            textView{
                textSize = 22f
            }
        }

        // Отображение граф.интерфейса
        val itemView = CategoryView(context)
        CategoryViewHolder(view = itemView)
    }

    // Заполнение ячейки: вход.данные(ячейка, позиция, по которой она заполняется)
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]

        Picasso.get().load(category.imageUrl).into(holder.view.pictureView)
        holder.view.titleView.text = category.title
        holder.view.priceView.text = category.price

        // Переход в другое окно
        holder.view.onClick {
            Preferences.currentCategoryUrl = category.url
            val categoryJson = JSON.nonstrict.stringify(category)
            context.startActivity<ProductsActivity>("category" to categoryJson)
        }
    }
}