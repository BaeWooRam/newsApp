package com.trip.news

import com.trip.news.model.NewsModel
import com.trip.news.model.rss.Item
import com.trip.news.model.rss.news.NewsContentsParser
import org.junit.After
import org.junit.Assert
import org.junit.Test

class NewsModelTest {
    private val rssModel = NewsModel()
    private var rssList:List<Item>? = null
    private val newsContentsParser = NewsContentsParser()

//    @Test
    fun `RSS 가져오기`(){
        readRss()

        for(item in rssList!!){
            println(item)
        }
    }

//    @Test
    fun `RSS Uri 체크`() {
        readRss()
        for (news in rssList!!) {
            Assert.assertTrue(newsContentsParser.isUrl(news.link))
        }
    }

    @Test
    fun `RSS News 본문 파싱 테스트`() {
        readRss()
        newsContentsParser.parserNewsContents(rssList!!)
    }

    @Synchronized
    private fun readRss() {
        val response = rssModel.newsService.getKrNewsList().execute()
        response.also {
            if (!response.isSuccessful)
                return@also

            val body = it.body()
            body?.run {
                rssList = channel.item
                for (news in rssList!!) {
                    Assert.assertFalse(news.title.isNullOrEmpty())
                    Assert.assertFalse(news.link.isNullOrEmpty())
                }
            }
        }
    }
}