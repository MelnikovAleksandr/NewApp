package ru.asmelnikov.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getSavedArticle: GetSavedArticle,
    val getSavedArticles: GetSavedArticles
)
