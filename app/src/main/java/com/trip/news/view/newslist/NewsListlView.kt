package com.trip.news.view.newslist

import androidx.paging.PagedList
import com.trip.news.model.rss.news.News

interface NewsListlView {
    fun onNetworkError(e: Throwable?)
    fun onUpdateNews(newsList:PagedList<News>?)
}