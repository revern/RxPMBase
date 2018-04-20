package com.example.revern.rxpmbase.ui.no

import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.base.BasePresentationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoPM(private val interactor: YesNoInteractor) : BasePresentationModel() {

    val refreshNoClick = Action<Unit>()

    val refreshNo = Command<String>(bufferSize = 1)

    override fun onCreate() {
        super.onCreate()

        refreshNoClick.observable.subscribe {
            loadGif()
        }.untilDestroy()

        loadGif()
    }

    private fun loadGif() {
        interactor.takeNo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    refreshNo.consumer.accept(it.image)
                }
    }

}
