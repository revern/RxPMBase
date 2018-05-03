package com.example.revern.rxpmbase.ui.yes

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.revern.rxpmbase.extensions.applyFadeAnimations
import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.base.BasePresentationModel
import com.example.revern.rxpmbase.ui.no.NoScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class YesPM(private val router: Router,
            private val interactor: YesNoInteractor)
    : BasePresentationModel() {

    val yesGif = State<String>()

    val refreshYesClick = Action<Unit>()
    val showNoClick = Action<Unit>()

    override fun onCreate() {
        super.onCreate()

        refreshYesClick.observable.subscribe { loadGif() }.untilDestroy()
        showNoClick.observable.subscribe { showNoScreen() }.untilDestroy()

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

    private fun showNoScreen() = router.replaceTopController(
            RouterTransaction.with(NoScreen())
                    .applyFadeAnimations())

}
