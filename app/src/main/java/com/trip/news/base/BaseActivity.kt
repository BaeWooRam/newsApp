package com.trip.news.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.trip.news.view.NewsApplication

open class BaseActivity :AppCompatActivity(),BaseView{

    override fun showDialog(message: String) {
        if (!isDestroyed)
            BaseDialog(this, message).show()
    }

    override fun progressON() {
        NewsApplication.getGlobalApplicationContext().progressON(this)
    }

    override fun progressOFF() {
        NewsApplication.getGlobalApplicationContext().progressOFF()
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