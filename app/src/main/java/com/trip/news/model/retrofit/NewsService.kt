package com.trip.news.model.retrofit

import com.trip.news.model.rss.News
import com.trip.news.model.rss.Rss
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {
    @GET("rss?hl=ko&gl=KR&ceid=KR:ko")
    fun getKrNewsList(): Call<Rss>

    @GET("rss?hl=en-US&gl=US&ceid=US:en")
    fun getEnNewsList(): Call<Rss>
}