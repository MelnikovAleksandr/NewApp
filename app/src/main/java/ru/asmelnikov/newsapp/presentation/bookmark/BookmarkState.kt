package ru.asmelnikov.newsapp.presentation.bookmark

import ru.asmelnikov.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)