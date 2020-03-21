package com.trip.news.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.trip.news.base.BaseAdapter
import com.trip.news.model.rss.news.News


object AdapterBinding {
    @JvmStatic
    @BindingAdapter("bind:item")
    fun bindRecyclerItem(
        recyclerView: RecyclerView,
        items:List<News>?
    ) {
        items?.let {
            val adapter = recyclerView.adapter as? BaseAdapter<News>
            adapter?.addItemList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:image","bind:imageError")
    fun bindImage(
        imageView: ImageView,
        url:String,
        errorDrawable:Drawable
    ) {
        val requestOptions = RequestOptions().error(errorDrawable).diskCacheStrategy(
            DiskCacheStrategy.NONE)

        Glide.with(imageView.context).applyDefaultRequestOptions(requestOptions).load(url).into(imageView)
    }
}