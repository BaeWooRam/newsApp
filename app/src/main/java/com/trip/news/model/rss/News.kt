package com.trip.news.model.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root
data class News(
    @Element
    val title: String,
    @Element
    val link: String,
    @Element
    val description: String,
    val image: String,
    val dcDate: String){

}