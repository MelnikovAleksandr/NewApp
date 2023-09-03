package ru.asmelnikov.newsapp.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.newsapp.domain.model.Article

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)