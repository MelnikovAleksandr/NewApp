package ru.asmelnikov.newsapp.presentation.details

import ru.asmelnikov.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}