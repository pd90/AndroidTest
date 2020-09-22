package com.example.androidtest.data

import android.icu.util.TimeUnit
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("jet2/api/v1/blogs")
    fun getNews(
            @Query("page") page: Int,
            @Query("limit") pageSize: Int
    ): Single<List<FeedsModel>>

    companion object {
        fun getService(): NetworkService {

            val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://5e99a9b1bc561b0016af3540.mockapi.io/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(NetworkService::class.java)
        }
        private val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
        private val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS) //Backend is really slow
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build()
    }
}