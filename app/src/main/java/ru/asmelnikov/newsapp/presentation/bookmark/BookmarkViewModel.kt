package ru.asmelnikov.newsapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.asmelnikov.newsapp.domain.usecases.news.GetSavedArticles
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedArticles: GetSavedArticles
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        getSavedArticles().onEach {
            _state.value = _state.value.copy(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }

}