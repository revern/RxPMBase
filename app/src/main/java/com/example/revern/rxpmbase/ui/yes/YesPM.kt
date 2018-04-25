package com.example.revern.rxpmbase.ui.yes

import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.base.BasePresentationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class YesPM(private val interactor: YesNoInteractor) : BasePresentationModel() {

    val yesGif = State<String>()

    val refreshYesClick = Action<Unit>()
    val showNoClick = Action<Unit>()

    val showNo = Command<Unit>(bufferSize = 0)

    override fun onCreate() {
        super.onCreate()

        refreshYesClick.observable.subscribe { loadGif() }.untilDestroy()
        showNoClick.observable.subscribe { showNo.consumer.accept(Unit) }.untilDestroy()

        loadGif()
    }

    private fun loadGif() {
        interactor.takeYes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {
                    yesGif.consumer.accept(it.image)
                }, onError = {
                    errors.consumer.accept(it.message)
                })
    }

}
