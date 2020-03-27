package com.trip.news.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel :ViewModel(){
    protected val disposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        if(!disposable.isDisposed)
            disposable.dispose()
    }
}