package com.trip.news.model.news

data class News(
    var id:Int,
    var title:String,
    var link:String,
    var imageURL:String,
    var description:String,
    var keyword:List<String>
){
}