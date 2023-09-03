package ru.asmelnikov.newsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.asmelnikov.newsapp.data.manager.LocalUserManagerImpl
import ru.asmelnikov.newsapp.data.remote.NewsApi
import ru.asmelnikov.newsapp.data.repository.NewsRepositoryImpl
import ru.asmelnikov.newsapp.domain.manager.LocalUserManager
import ru.asmelnikov.newsapp.domain.repository.NewsRepository
import ru.asmelnikov.newsapp.domain.usecases.app_entry.AppEntryUseCases
import ru.asmelnikov.newsapp.domain.usecases.app_entry.ReadAppEntryUseCase
import ru.asmelnikov.newsapp.domain.usecases.app_entry.SaveAppEntryUseCase
import ru.asmelnikov.newsapp.domain.usecases.news.GetNewsUseCase
import ru.asmelnikov.newsapp.domain.usecases.news.NewsUseCases
import ru.asmelnikov.newsapp.domain.usecases.news.SearchNewsUseCase
import ru.asmelnikov.newsapp.utils.Constants.BASE_URL
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

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNewsUseCase = GetNewsUseCase(newsRepository = newsRepository),
            searchNewsUseCase = SearchNewsUseCase(newsRepository = newsRepository)
        )
    }

}