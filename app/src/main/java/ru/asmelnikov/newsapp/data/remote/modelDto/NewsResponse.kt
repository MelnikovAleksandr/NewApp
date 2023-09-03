package ru.asmelnikov.newsapp.data.remote.modelDto

import ru.asmelnikov.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)