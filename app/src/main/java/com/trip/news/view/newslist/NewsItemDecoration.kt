package com.trip.news.view.newslist

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect

import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trip.news.R

class NewsItemDecoration(private val context: Context): DividerItemDecoration(context, LinearLayoutManager.VERTICAL) {

    private val topBottomSize: Int by lazy {
        context.resources.getDimension(R.dimen.item_margin_top_bottom).toInt()
    }
    private val letRightSize: Int by lazy {
        context.resources.getDimension(R.dimen.item_margin_left_right).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        //상하 설정
        if (position == 0) {
            // 첫번 째 줄 아이템
            outRect.top = topBottomSize
        }

        outRect.left = letRightSize
        outRect.right = letRightSize
    }
}
