package com.trip.news.model.dto

data class News(
    var title: String,
    var keywordList: List<String>,
    var imageUri: String
)