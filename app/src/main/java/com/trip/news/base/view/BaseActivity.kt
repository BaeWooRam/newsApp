package com.trip.news.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.trip.news.model.NetworkState
import com.trip.news.utils.NetworkUtil
import com.trip.news.view.NewsApplication

open class BaseActivity :AppCompatActivity, BaseView {
    constructor(@LayoutRes layout:Int) : super(layout)
    constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showDialog(message: String) {
        if (!isDestroyed)
            BaseDialog(this, message).show()
    }

    override fun progressON(type: NetworkState.ProgressType) {
        NewsApplication.getGlobalApplicationContext().progressON(this, type)
    }

    override fun progressOFF() {
        NewsApplication.getGlobalApplicationContext().progressOFF()
    }

    override fun isNetwork() {
        if(!NetworkUtil.isNetwork(this))
            finish()
        else
            NetworkUtil
                .PingAsyTask(this)
                .execute("http://clients3.google.com/generate_204")
    }

    fun <B : ViewDataBinding> setDataBindingContentView(@LayoutRes layoutResID: Int): B {
        val binding = DataBindingUtil.setContentView<B>(
            this,
            layoutResID
        )

        binding.lifecycleOwner = this

        return binding
    }
}