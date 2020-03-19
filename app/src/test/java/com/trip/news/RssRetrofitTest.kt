package com.trip.news

import com.trip.news.model.rss.RssRetrofitModel
import org.junit.Test

class RssRetrofitTest {
    private val rssModel = RssRetrofitModel()

    @Test
    fun readRss() {
        val response = rssModel.newsService.getKrNewsList().execute()
        response.also {
            if (!response.isSuccessful)
                return@also

            val body = it.body()
            body?.run {
                for (news in channel.item) {
                    println(news)
                }
            }
        }
    }
}