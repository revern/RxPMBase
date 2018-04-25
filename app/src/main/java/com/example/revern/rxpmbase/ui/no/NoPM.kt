package com.example.revern.rxpmbase.ui.no

import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.base.BasePresentationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NoPM(private val interactor: YesNoInteractor) : BasePresentationModel() {

    val noGif = State<String>()

    val refreshNoClick = Action<Unit>()
    val showYesClick = Action<Unit>()

    val showYes = Command<Unit>(bufferSize = 0)

    override fun onCreate() {
        super.onCreate()

        refreshNoClick.observable.subscribe { loadGif() }.untilDestroy()
        showYesClick.observable.subscribe { showYes.consumer.accept(Unit) }.untilDestroy()

        loadGif()
    }

    private fun loadGif() {
        interactor.takeNo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {
                    noGif.consumer.accept(it.image)
                }, onError = {
                    errors.consumer.accept(it.message)
                })
    }

}
