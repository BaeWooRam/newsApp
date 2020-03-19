package com.trip.news.model.rss

import android.util.Log
import com.trip.news.model.retrofit.NewsService
import com.trip.news.model.retrofit.ServiceBuilder
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class RssRetrofitModel {
    val newsService = ServiceBuilder.getNewsService(
    "https://news.google.com/",
    NewsService::class.java
    )

}