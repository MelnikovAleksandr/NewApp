package ru.asmelnikov.newsapp.domain.usecases.app_entry

import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.newsapp.domain.manager.LocalUserManager

class ReadAppEntryUseCase(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> = localUserManager.readsAppEntry()

}