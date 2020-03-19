package com.trip.news.model

import com.trip.news.model.retrofit.NewsService
import com.trip.news.model.retrofit.ServiceBuilder
import com.trip.news.model.rss.RssRetrofitModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        RssRetrofitModel()
    }
}