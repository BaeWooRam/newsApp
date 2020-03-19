package com.trip.news

import com.trip.news.model.rss.RssModel
import com.trip.news.model.rss.RssRetrofitModel
import org.junit.Before
import org.junit.Test

class RssRetrofitTest {
    private val rssModel = RssRetrofitModel()

    @Test
    fun readRss() {
        val response = rssModel.newsService.getNewsList().execute()
        response.also {
            if (!response.isSuccessful)
                return@also

            val newsList = it.body()
            newsList?.run {
                for (news in this) {
                    println(news)
                }
            }
        }
    }
}