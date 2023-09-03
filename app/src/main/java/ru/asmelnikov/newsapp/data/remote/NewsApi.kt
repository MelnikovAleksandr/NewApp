package ru.asmelnikov.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.asmelnikov.newsapp.data.remote.modelDto.NewsResponse
import ru.asmelnikov.newsapp.utils.Constants.API_KEY

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}