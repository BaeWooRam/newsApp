package com.trip.news.model.rss

import com.trip.news.model.api.RssService
import io.reactivex.Observable

class RssContentsParser(private val rssService: RssService) {

    /**
     * 언어 타입에 따라 RSS 들고 오기
     */
    fun getNewsRssWork(languageType:RssService.LanguageType): Observable<Rss> {
        return when(languageType){
            RssService.LanguageType.EN ->{
                rssService.getEnNewsList()
            }
            RssService.LanguageType.KR ->{
                rssService.getKrNewsList()
            }
        }
    }

}