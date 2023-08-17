package ru.asmelnikov.newsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.asmelnikov.newsapp.data.manager.LocalUserManagerImpl
import ru.asmelnikov.newsapp.domain.manager.LocalUserManager
import ru.asmelnikov.newsapp.domain.usecases.AppEntryUseCases
import ru.asmelnikov.newsapp.domain.usecases.ReadAppEntryUseCase
import ru.asmelnikov.newsapp.domain.usecases.SaveAppEntryUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntryUseCase = ReadAppEntryUseCase(localUserManager),
        saveAppEntryUseCase = SaveAppEntryUseCase(localUserManager)
    )

}