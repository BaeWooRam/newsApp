package com.trip.news.model.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PositionalDataSource
import com.trip.news.model.NetworkState
import com.trip.news.model.retrofit.RssService
import com.trip.news.model.rss.Item
import com.trip.news.model.rss.Rss
import com.trip.news.model.rss.news.News
import com.trip.news.model.rss.news.NewsContentsParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RssDataSource(
    private val newsContentsParser: NewsContentsParser,
    private val rss: Rss
): PositionalDataSource<News>() {
    private val tag = javaClass.simpleName

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<News>
    ) {
        Log.i(tag, "Initial Loading, start: ${params.requestedStartPosition}, size: ${params.requestedLoadSize}")
        GlobalScope.launch(Dispatchers.IO) {
            val data = newsContentsParser.parserNewsContents(getRssItem())
            callback.onResult(data, 0)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<News>) {
        Log.i(tag, "Range Loading, start: ${params.startPosition}, size: ${params.loadSize}")
        GlobalScope.launch(Dispatchers.IO) {
            val data = newsContentsParser.parserNewsContents(getRssItem())
            callback.onResult(data)
        }
    }

    private fun getRssItem():List<Item>{
        val itemList = ArrayList<Item>()
        var count = 0

        for(item in rss.channel.item){
            if(count==NewsContentsParser.PAGE_NEWS_SIZE) {
                itemList.add(item)
                count++
            }
            else
                return itemList
        }

        return itemList
    }
}