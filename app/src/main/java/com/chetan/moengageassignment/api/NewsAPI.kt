package com.chetan.moengageassignment.api

import com.chetan.moengageassignment.models.NewsListResponse
import retrofit2.Response
import retrofit2.http.*

interface NewsAPI {


    @GET("news-api-feed/staticResponse.json")
    suspend fun getNewsList(): Response<NewsListResponse>

//    @GET("https://quotable.io/quotes")
//    suspend fun getQuotes(@Query("page") pageNo: Int): Response<QuoteList>


}