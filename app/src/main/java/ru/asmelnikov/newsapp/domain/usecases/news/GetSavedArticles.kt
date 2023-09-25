package ru.asmelnikov.newsapp.domain.usecases.news

import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.newsapp.domain.model.Article
import ru.asmelnikov.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}