package com.trip.news

import com.trip.news.model.appModule
import com.trip.news.model.retrofit.RssService
import com.trip.news.model.rss.Item
import com.trip.news.model.rss.news.NewsContentsParser
import org.junit.*
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject


class NewsModelTest:KoinTest{

    private val rssService:RssService by inject()
    private var rssList:List<Item>? = null
    private val newsContentsParser = NewsContentsParser()

    @Before
    fun init(){
        startKoin {
            modules(appModule)
        }

        readRss()
    }

    @Test
    fun `RSS 가져오기`(){
        for(item in rssList!!){
            println(item)
        }
    }

    @Test
    fun `RSS Uri 체크`() {
        for (news in rssList!!) {
            Assert.assertTrue(newsContentsParser.isUrl(news.link))
        }
    }

    @Test
    fun `RSS News 본문 파싱 테스트`() {
        newsContentsParser.parserNewsContents(rssList!!)
    }

    private fun readRss() {
        val response = rssService.getTestKrNewsList().execute()
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

    @After
    fun finish() {
        stopKoin()
    }

}