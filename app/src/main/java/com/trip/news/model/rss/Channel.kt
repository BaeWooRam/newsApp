package com.trip.news.model.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel",strict = false)
data class Channel(
    @param:ElementList(entry = "item", inline = true)
    @field:ElementList(entry = "item", inline = true)
    var item: List<Item>,

    @param:Element(name = "lastBuildDate")
    @field:Element(name = "lastBuildDate")
    var lastBuildDate:String
)