package com.trip.news.view.newslist

import com.trip.news.model.rss.news.News

interface NewsListView {
    fun onError(e:Throwable?)
    fun onLoading()
    fun onInit()
}