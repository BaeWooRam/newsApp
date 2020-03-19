package com.trip.news.model.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item (
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String,

    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: String
)