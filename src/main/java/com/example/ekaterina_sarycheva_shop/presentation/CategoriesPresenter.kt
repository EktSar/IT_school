package com.example.ekaterina_sarycheva_shop.presentation

import com.example.ekaterina_sarycheva_shop.data.RequestMaker
import com.example.ekaterina_sarycheva_shop.db
import com.example.ekaterina_sarycheva_shop.entitins.CategoriesList
import com.example.ekaterina_sarycheva_shop.presentation.base.LifecyclePresenter
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.serialization.json.JSON


// Логика окон
class CategoriesPresenter(
        private val requestMaker: RequestMaker
) : LifecyclePresenter<CategoriesView>() {

    // При создании отображения показать загрузку
    override fun onCreateView(view: CategoriesView) {
        super.onCreateView(view)
        view.displayLoading()
    }

    // Когда появляется экран, осуществляется загрузка категорий
    override fun onAppear() {
        super.onAppear()
        loadCategories()
    }

    private fun loadCategories() { // Загрузка категорий

        // Получение корутин + получение категорий
        GlobalScope.launch(Dispatchers.Main) {

            val categories = try{ // Запрос у интернета

                val json = async(Dispatchers.IO){
                    requestMaker.make("https://gist.githubusercontent.com/EktSar/9eca572db8c26391df1166611f5d683a/raw/7ba46ff7e1cf9d738be6114cbda182b6c6f4aa17/categories.json") // "https://api.myjson.com/bins/apiko"
                }.await()

                // Из результата запроса разобрать как json
                val categoriesList: CategoriesList = JSON.parse(json)

                // Записать в бд все категории
                //БАЗА ДАННЫХ/////////////////////////////////////////////////////////
                async(Dispatchers.IO){
                    categoriesList.categories.forEach { category -> // Все категории запишутся
                        db.categoriesDao().add(category)
                    }
                }.await()
                //////////////////////////////////////////////////////////////////////

                // В итоге, в val categories записать categoriesList.categories
                categoriesList.categories

            } catch (error: Exception) { // Если было вызвано исключение
                // Получи все категории из бд
                val categoriesFromDb = async(Dispatchers.IO) {
                    db.categoriesDao().all()
                }.await()
                // И в val categories положаться категории из бд
                categoriesFromDb
            }

            // Если отображение существует, то отображается список категорий
            view?.displayCategories(categories) // Реализовано в устройстве, ? == выполняется, если notNULL
        }

    }

}