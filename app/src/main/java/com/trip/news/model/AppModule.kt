package com.trip.news.model

import org.koin.dsl.module

val appModule = module {
    single {
        RssRetrofitModel()
    }
}