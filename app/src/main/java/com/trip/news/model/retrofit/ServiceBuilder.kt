package com.trip.news.model.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Retrofit Service 빌더
 */
object ServiceBuilder {
    private val client = getOkHttpInterceptor()

    fun <T>getNewsService(url:String, clazz:Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
            .create(clazz)
    }

    private fun getOkHttpInterceptor():OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}