package com.trip.news.model.rss.news

import org.simpleframework.xml.ElementMap
import org.simpleframework.xml.Root


@Deprecated("Jsoup으로 본문 분석하기로함")
@Root(name = "head", strict = false)
data class Head(
    @ElementMap(
        entry = "mata",
        key = "property",
        value = "contents",
        attribute = true,
        inline = true
    )
    val mataMap: Map<String, String>
) {
}
