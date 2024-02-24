package com.example.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(): List<ArticleRaw>{
        val articlesDB = dataSource.getAllArticles()

        if (articlesDB.isEmpty()){
            val fetchedArticles = service.fetchArticles()
            dataSource.insertArticles(fetchedArticles)
            return  fetchedArticles
        }

        return articlesDB
    }
}