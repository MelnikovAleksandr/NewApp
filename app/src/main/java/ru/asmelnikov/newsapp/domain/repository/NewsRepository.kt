package ru.asmelnikov.newsapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.newsapp.domain.model.Article

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?
}