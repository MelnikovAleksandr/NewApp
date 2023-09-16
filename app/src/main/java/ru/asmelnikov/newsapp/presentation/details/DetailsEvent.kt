package ru.asmelnikov.newsapp.presentation.details

sealed class DetailsEvent {
    object SaveArticleEvent : DetailsEvent()
}