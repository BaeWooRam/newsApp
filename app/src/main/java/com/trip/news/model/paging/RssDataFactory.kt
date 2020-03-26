package com.trip.news.model.paging

import androidx.paging.DataSource
import com.trip.news.model.rss.Rss
import com.trip.news.model.news.News
import com.trip.news.model.news.NewsContentsParser
import com.trip.news.viewmodel.NewsListViewModel

class RssDataFactory(
    private val rss: Rss,
    private val newsContentsParser: NewsContentsParser,
    private val viewModel: NewsListViewModel
) : DataSource.Factory<Int, News>() {

    private var rssDataSource: RssDataSource? = null

    override fun create(): DataSource<Int, News> {
        rssDataSource =
            RssDataSource(newsContentsParser = newsContentsParser, rss = rss, viewModel = viewModel)

        return rssDataSource!!
    }

}