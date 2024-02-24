package com.example.dailypulse.articles

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(
    private val repo: ArticlesService
) {
    suspend fun getArticles():List<Article>{
        val articleRaw = repo.fetchArticles()
        return mapArticles(articleRaw)
    }

    private fun mapArticles(articleRaw: List<ArticleRaw>): List<Article> = articleRaw.map{ raw->
        Article(
            raw.title,
            raw.desc ?: "Click to find out more",
            getDaysAgoString(raw.date),
            raw.imageUrl ?: "https://imgmedia.larepublica.pe/640x374/larepublica/original/2024/02/21/65d6829511bb376bf307a204.webp"
        )
    }

    private fun getDaysAgoString(date: String) : String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result

    }
}