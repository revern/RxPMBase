package com.example.revern.rxpmbase.di.modules

import android.content.Context
import com.example.revern.rxpmbase.api.Api
import com.example.revern.rxpmbase.constants.CACHE_DIR_SIZE
import com.example.revern.rxpmbase.constants.READ_TIMEOUT
import com.example.revern.rxpmbase.utils.addStethoInterceptors
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit

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
    httpClientBuilder.cache(Cache(cachedDir, CACHE_DIR_SIZE))
    httpClientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    addStethoInterceptors(httpClientBuilder)
    httpClientBuilder.addInterceptor { chain ->
        //            if (userSettings.userHasToken()) {
        //                Request request = chain.request().newBuilder()
        //                    .addHeader("X-User-Token", userSettings.getToken())
        //                    .addHeader("X-User-Phone-Number", userSettings.getPhone())
        //                    .build();
        //                return chain.proceed(request);
        //            }
        chain.proceed(chain.request())
    }
    return httpClientBuilder.build()
}
