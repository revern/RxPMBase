package com.example.revern.rxpmbase.utils

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import com.facebook.stetho.Stetho


fun initializeStetho(context: Context) {
    Stetho.initializeWithDefaults(context)
}

fun addStethoInterceptors(httpClientBuilder: OkHttpClient.Builder) {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    httpClientBuilder.addInterceptor(interceptor)
    httpClientBuilder.addNetworkInterceptor(StethoInterceptor())
}