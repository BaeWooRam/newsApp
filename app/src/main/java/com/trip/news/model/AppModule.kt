package com.trip.news.model

import com.trip.news.model.retrofit.RssService
import com.trip.news.model.retrofit.ServiceBuilder
import com.trip.news.model.news.NewsContentsParser
import com.trip.news.viewmodel.NewsListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        ServiceBuilder.getNewsService(
            "https://news.google.com/",
            RssService::class.java
        )
    }

    single {
        NewsContentsParser()
    }

    viewModel {
        NewsListViewModel(get(), get())
    }
}