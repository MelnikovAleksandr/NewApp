package ru.asmelnikov.newsapp.domain.usecases.news

import ru.asmelnikov.newsapp.domain.model.Article
import ru.asmelnikov.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}