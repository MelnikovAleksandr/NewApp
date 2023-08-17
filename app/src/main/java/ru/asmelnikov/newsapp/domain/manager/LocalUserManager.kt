package ru.asmelnikov.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppEntry()

    fun readsAppEntry(): Flow<Boolean>
}