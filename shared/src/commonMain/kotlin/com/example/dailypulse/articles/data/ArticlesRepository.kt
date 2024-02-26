package com.example.dailypulse.articles.data

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw>{

        if (forceFetch){
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDB = dataSource.getAllArticles()
        println("Go ${articlesDB.size} from the database!!")


        if (articlesDB.isEmpty()){
            return fetchArticles()
        }

        return articlesDB
    }


    private suspend fun fetchArticles(): List<ArticleRaw>{
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return  fetchedArticles
    }
}