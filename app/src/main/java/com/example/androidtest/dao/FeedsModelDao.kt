package com.example.androidtest.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidtest.data.FeedsModel
import com.example.androidtest.data.NewsDataSource

@Dao
interface FeedsModelDao {

    @Query("SELECT * FROM FeedsModel")
    fun getAll(): LiveData<List<FeedsModel>>

    @Query("SELECT * FROM FeedsModel")
    fun getAllPaged(): DataSource.Factory<Int, FeedsModel>

    @Insert
    fun insertAll(feedsModel: List<FeedsModel>)

    @Delete
    fun delete(feedsModel: FeedsModel)
}