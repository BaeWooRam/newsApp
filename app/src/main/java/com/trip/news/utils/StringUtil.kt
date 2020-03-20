package com.trip.news.utils

object StringUtil{
    fun replaceSpecialCharacters(str: String, replacement:String): String {
        val match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]"
        return str.replace(match.toRegex(), replacement)
    }

}