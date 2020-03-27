package com.trip.news.model.news

import androidx.paging.DataSource
import com.trip.news.model.rss.Rss
import com.trip.news.viewmodel.NewsListViewModel

class NewsDataFactory(
    private val rss: Rss,
    private val newsContentsParser: NewsContentsParser,
    private val viewModel: NewsListViewModel
) : DataSource.Factory<Int, News>() {

    private var rssDataSource: NewsDataSource? = null

    override fun create(): DataSource<Int, News> {
        rssDataSource =
            NewsDataSource(
                newsContentsParser = newsContentsParser,
                rss = rss,
                viewModel = viewModel
            )

        return rssDataSource!!
    }

}