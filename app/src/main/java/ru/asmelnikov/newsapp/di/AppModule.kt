package ru.asmelnikov.newsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.asmelnikov.newsapp.data.local.NewsDao
import ru.asmelnikov.newsapp.data.local.NewsDatabase
import ru.asmelnikov.newsapp.data.local.NewsTypeConverter
import ru.asmelnikov.newsapp.data.manager.LocalUserManagerImpl
import ru.asmelnikov.newsapp.data.remote.NewsApi
import ru.asmelnikov.newsapp.data.repository.NewsRepositoryImpl
import ru.asmelnikov.newsapp.domain.manager.LocalUserManager
import ru.asmelnikov.newsapp.domain.repository.NewsRepository
import ru.asmelnikov.newsapp.domain.usecases.app_entry.AppEntryUseCases
import ru.asmelnikov.newsapp.domain.usecases.app_entry.ReadAppEntryUseCase
import ru.asmelnikov.newsapp.domain.usecases.app_entry.SaveAppEntryUseCase
import ru.asmelnikov.newsapp.domain.usecases.news.DeleteArticle
import ru.asmelnikov.newsapp.domain.usecases.news.GetNewsUseCase
import ru.asmelnikov.newsapp.domain.usecases.news.GetSavedArticle
import ru.asmelnikov.newsapp.domain.usecases.news.GetSavedArticles
import ru.asmelnikov.newsapp.domain.usecases.news.NewsUseCases
import ru.asmelnikov.newsapp.domain.usecases.news.SearchNewsUseCase
import ru.asmelnikov.newsapp.domain.usecases.news.UpsertArticle
import ru.asmelnikov.newsapp.utils.Constants.BASE_URL
import ru.asmelnikov.newsapp.utils.Constants.NEWS_DB_NAME
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
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNewsUseCase = GetNewsUseCase(newsRepository = newsRepository),
            searchNewsUseCase = SearchNewsUseCase(newsRepository = newsRepository),
            deleteArticle = DeleteArticle(newsRepository = newsRepository),
            upsertArticle = UpsertArticle(newsRepository = newsRepository),
            getSavedArticle = GetSavedArticle(newsRepository = newsRepository),
            getSavedArticles = GetSavedArticles(newsRepository = newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DB_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}