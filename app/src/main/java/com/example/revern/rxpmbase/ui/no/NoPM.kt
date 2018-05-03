package com.example.revern.rxpmbase.ui.no

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.revern.rxpmbase.extensions.applyFadeAnimations
import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.base.BasePresentationModel
import com.example.revern.rxpmbase.ui.yes.YesScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NoPM(private val router: Router,
           private val interactor: YesNoInteractor)
    : BasePresentationModel() {

    val noGif = State<String>()

    val refreshNoClick = Action<Unit>()
    val showYesClick = Action<Unit>()

    override fun onCreate() {
        super.onCreate()

        refreshNoClick.observable.subscribe { loadGif() }.untilDestroy()
        showYesClick.observable.subscribe { showYesScreen() }.untilDestroy()

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

    private fun showYesScreen() = router.replaceTopController(
            RouterTransaction.with(YesScreen())
                    .applyFadeAnimations())

}
