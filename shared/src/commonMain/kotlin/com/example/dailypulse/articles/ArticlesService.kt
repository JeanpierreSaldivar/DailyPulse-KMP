package com.example.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpclient: HttpClient) {

    private val country = "us"
    private val category = "business"
    private val apiKey = "4f204e93a23d4267b42bdac5fc3d5912"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse = httpclient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }
}