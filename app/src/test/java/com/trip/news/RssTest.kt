package com.trip.news

import com.trip.news.model.rss.RssModel
import org.junit.Before
import org.junit.Test

@Deprecated("현재 Retrofit으로 바꾸는 중")
class RssTest {
    private val rssModel = RssModel()
    @Before
    fun init(){
        rssModel.requestURL = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    }

    @Test
    fun readRss(){
        rssModel.test()
    }
}