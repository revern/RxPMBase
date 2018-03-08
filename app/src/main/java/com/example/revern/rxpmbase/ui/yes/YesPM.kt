package com.example.revern.rxpmbase.ui.yes

import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.base.BasePresentationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YesPM(private val interactor: YesNoInteractor) : BasePresentationModel() {

    val refreshYesClick = Action<Unit>()

    val refreshYes = Command<String>(bufferSize = 1)

    override fun onCreate() {
        super.onCreate()

        refreshYesClick.observable.subscribe {
            loadGif()
        }.untilDestroy()

        loadGif()
    }

    private fun loadGif() {
        interactor.takeYes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    refreshYes.consumer.accept(it.image)
                }
    }
}