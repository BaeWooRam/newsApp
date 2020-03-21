package com.trip.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.trip.news.base.BaseViewModel
import com.trip.news.model.NetworkState
import com.trip.news.model.retrofit.RssService
import com.trip.news.model.rss.news.News
import com.trip.news.model.rss.news.NewsContentsParser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class NewsListViewModel(
    private val rssService: RssService,
    private val newsContentsParser: NewsContentsParser
) : BaseViewModel() {
    var newsList: MutableLiveData<List<News>> = MutableLiveData()

    private var currentNewsState: NetworkState<List<News>> by Delegates.observable(
        NetworkState.Init(),
        { _: KProperty<*>, _: NetworkState<List<News>>, newState: NetworkState<List<News>> ->
            when (newState) {
                is NetworkState.Init -> {

                }

                is NetworkState.Loading -> {

                }

                is NetworkState.Success<List<News>> -> {

                }
                is NetworkState.Error -> {

                }
            }
        })

    fun getNews() {
        val work = rssService.getKrNewsList()

        work.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                currentNewsState = NetworkState.Init()
            }
            .doOnSubscribe {
                disposable.add(it)
                currentNewsState = NetworkState.Loading()
            }
            .subscribe(Consumer {
//                responseHandle(it)
            }, Consumer {
                currentNewsState = NetworkState.Error(it)
            })
            .dispose()
    }
}