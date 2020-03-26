package com.trip.news.model.news

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Deprecated("Jsoup으로 본문 분석하기로함")
@Root(name = "html", strict = false)
data class Html(
    @param:Element(name = "head")
    @field:Element(name = "head")
    var head: Head
)