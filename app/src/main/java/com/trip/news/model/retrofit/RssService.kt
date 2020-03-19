package com.trip.news.model.retrofit

import com.trip.news.model.rss.Rss
import retrofit2.Call
import retrofit2.http.GET

interface RssService {

    /**
     * 구글 한국 뉴스
     */
    @GET("rss?hl=ko&gl=KR&ceid=KR:ko")
    fun getKrNewsList(): Call<Rss>


    /**
     * 구글 미국 뉴스
     */
    @GET("rss?hl=en-US&gl=US&ceid=US:en")
    fun getEnNewsList(): Call<Rss>
}