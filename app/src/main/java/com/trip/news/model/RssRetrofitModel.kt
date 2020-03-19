package com.trip.news.model

import com.trip.news.model.retrofit.RssService
import com.trip.news.model.retrofit.ServiceBuilder

class RssRetrofitModel {
    val newsService = ServiceBuilder.getNewsService(
        "https://news.google.com/",
        RssService::class.java
    )

}