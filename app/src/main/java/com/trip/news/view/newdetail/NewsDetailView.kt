package com.trip.news.view.newdetail

import com.trip.news.model.rss.news.News

interface NewsDetailView {
    fun onError(e:Throwable?)
    fun onLoading()
    fun onInit()
}