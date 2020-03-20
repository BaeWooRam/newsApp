package com.trip.news.model

import com.trip.news.model.retrofit.RssService
import com.trip.news.model.retrofit.ServiceBuilder
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL
import java.util.regex.Pattern

class NewsModel {
    val newsService = ServiceBuilder.getNewsService(
        "https://news.google.com/",
        RssService::class.java
    )
}