package com.trip.news.model.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

@Root(name = "channel",strict = false)
data class Channel(
    @param:ElementList(entry = "item", inline = true)
    @field:ElementList(entry = "item", inline = true)
    var item: List<Item>,

    @param:Element(name = "lastBuildDate")
    @field:Element(name = "lastBuildDate")
    var lastBuildDate:String
){
    fun getItemQueue():Queue<Item>{
        var tempQueue: Queue<Item> = LinkedList<Item>()
        for(item in item){
            tempQueue.add(item)
        }

        return tempQueue
    }
}