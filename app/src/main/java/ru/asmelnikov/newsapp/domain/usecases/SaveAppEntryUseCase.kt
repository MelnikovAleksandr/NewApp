package ru.asmelnikov.newsapp.domain.usecases

import ru.asmelnikov.newsapp.domain.manager.LocalUserManager

class SaveAppEntryUseCase(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }

}