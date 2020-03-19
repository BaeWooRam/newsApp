package com.trip.news.model.retrofit

import com.trip.news.model.rss.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("rss?hl=ko&gl=KR&ceid=KR:ko")
    fun getNewsList(): Call<List<News>>
}