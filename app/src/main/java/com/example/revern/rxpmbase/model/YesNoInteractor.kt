package com.example.revern.rxpmbase.model

import com.example.revern.rxpmbase.api.Api
import com.example.revern.rxpmbase.model.entity.YesNoResponse
import io.reactivex.Flowable

class YesNoInteractor(private val api: Api) {

    fun takeYes(): Flowable<YesNoResponse> = api.take("yes")

    fun takeNo(): Flowable<YesNoResponse> = api.take("no")

}
