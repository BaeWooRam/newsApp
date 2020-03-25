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
import java.util.*
import kotlin.collections.ArrayList

class RssDataSource(
    private val newsContentsParser: NewsContentsParser,
    private val rss: Rss
) : PositionalDataSource<News>() {
    private val tag = javaClass.simpleName
    private val itemQueue = rss.channel.getItemQueue()

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<News>
    ) {
        Log.i(
            tag,
            "Initial Loading, rss : ${rss.channel.item.size}, itemQueue : ${itemQueue.size}, start: ${params.requestedStartPosition}, size: ${params.requestedLoadSize}"
        )
        GlobalScope.launch(Dispatchers.IO) {
            val rssItem = getRssItem()
            val data = newsContentsParser.parserNewsContents(rssItem)
            callback.onResult(data, 0)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<News>) {
        Log.i(tag, "Range Loading, rss : ${rss.channel.item.size}, itemQueue : ${itemQueue.size}, start: ${params.startPosition}, size: ${params.loadSize}")
        GlobalScope.launch(Dispatchers.IO) {
            val rssItem = getRssItem()
            val data = newsContentsParser.parserNewsContents(rssItem)
            callback.onResult(data)
        }
    }

    private fun getRssItem(): List<Item> {
        val itemList = ArrayList<Item>()
        var count = 0

        while (itemQueue.isNotEmpty()){
            if (count == NewsContentsParser.PAGE_NEWS_SIZE)
                return itemList
            else {
                val temp = itemQueue.poll()

                if (temp != null) {
                    itemList.add(temp)
                    count++
                } else
                    return itemList
            }
        }

        return itemList
    }
}