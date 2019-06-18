package com.example.revern.rxpmbase.api

import com.example.revern.rxpmbase.BuildConfig
import com.example.revern.rxpmbase.model.entity.YesNoResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(BuildConfig.API_BASE_URL)
    fun take(@Query("force") value: String): Flowable<YesNoResponse>
}
