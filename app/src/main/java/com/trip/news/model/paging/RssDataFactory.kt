package com.trip.news.model.paging

import androidx.paging.DataSource
import com.trip.news.model.rss.Rss
import com.trip.news.model.rss.news.News
import com.trip.news.model.rss.news.NewsContentsParser

class RssDataFactory(
    private val newsContentsParser: NewsContentsParser,
    private val rss:Rss
) : DataSource.Factory<Int, News>() {

    private var rssDataSource: RssDataSource? = null

    override fun create(): DataSource<Int, News> {
        rssDataSource = RssDataSource(newsContentsParser, rss)
        return rssDataSource!!
    }

}