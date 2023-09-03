package ru.asmelnikov.newsapp.domain.usecases.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.newsapp.domain.model.Article
import ru.asmelnikov.newsapp.domain.repository.NewsRepository

class SearchNewsUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}
