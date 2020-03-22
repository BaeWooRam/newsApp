package com.trip.news.model.rss.news

data class News(
    var title:String,
    var link:String,
    var imageURL:String,
    var description:String,
    var keyword:List<String>
){
}