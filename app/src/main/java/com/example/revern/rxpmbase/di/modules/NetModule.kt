package com.example.revern.rxpmbase.di.modules

import android.content.Context
import com.example.revern.rxpmbase.api.Api
import com.example.revern.rxpmbase.constants.CACHE_DIR_SIZE
import com.example.revern.rxpmbase.constants.READ_TIMEOUT
import com.example.revern.rxpmbase.utils.addStethoInterceptors
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

fun netModule(baseUrl: String) = Kodein.Module {

    bind<File>() with singleton { instance<Context>().cacheDir }

    bind<OkHttpClient>() with singleton { createHttpClient(instance()) }

    bind<Api>() with singleton {
        Retrofit.Builder()
                .client(instance())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(instance()))
                .build()
                .create(Api::class.java)
    }
}

private fun createHttpClient(cachedDir: File): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
            .cache(Cache(cachedDir, CACHE_DIR_SIZE))
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    addStethoInterceptors(httpClientBuilder)
    return httpClientBuilder.build()
}
