package com.example.androidtest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.androidtest.data.*
import io.reactivex.disposables.CompositeDisposable

class NewsListViewModel : ViewModel() {

    private val networkService = NetworkService.getService()
    var newsList: LiveData<PagedList<FeedsModel>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val newsDataSourceFactory: NewsDataSourceFactory

    init {
        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build()
        newsList = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap(
            newsDataSourceFactory.newsDataSourceLiveData,
            NewsDataSource::state
    )

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}