package com.trip.news.view.newslist

import com.trip.news.model.rss.news.News

interface NewsListlView {
    fun onNetworkError(e: Throwable?)
    fun onUpdateNews(newsList:List<News>)
}