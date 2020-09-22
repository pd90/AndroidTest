package com.example.androidtest.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val networkService: NetworkService)
    : DataSource.Factory<Int, FeedsModel>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, FeedsModel> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}