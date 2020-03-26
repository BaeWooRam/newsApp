package com.trip.news.model.news

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Deprecated("Jsoup으로 본문 분석하기로함")
@Root(name = "meta", strict = false)
data class Meta(
    @param:Attribute(name = "property")
    @field:Attribute(name = "property")
    var property: String,

    @param:Attribute(name = "content")
    @field:Attribute(name = "content")
    var content: String
) {
}
