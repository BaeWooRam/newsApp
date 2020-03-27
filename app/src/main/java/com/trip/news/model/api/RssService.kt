package com.trip.news.model.api

import com.trip.news.model.rss.Rss
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RssService {
    companion object{
        const val RSS_KR_URL = "rss?hl=ko&gl=KR&ceid=KR:ko"
        const val RSS_EN_URL = "rss?hl=en-US&gl=US&ceid=US:en"
    }
    /**
     * 테스트용 구글 한국 뉴스
     */
    @GET(RSS_KR_URL)
    fun getTestKrNewsList(): Call<Rss>
    
    /**
     * 테스트용 구글 미국 뉴스
     */
    @GET(RSS_EN_URL)
    fun getTestEnNewsList(): Call<Rss>

    /**
     * 구글 한국 뉴스
     */
    @GET(RSS_KR_URL)
    fun getKrNewsList(): Observable<Rss>

    /**
     * 테스트용 구글 미국 뉴스
     */
    @GET(RSS_EN_URL)
    fun getEnNewsList(): Observable<Rss>

}