package com.example.revern.rxpmbase.ui.yes

import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bumptech.glide.Glide
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.example.revern.rxpmbase.ui.no.NoScreen
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.clicks

class YesScreen : BaseScreen<YesPM>() {

    override val screenLayout = R.layout.screen_yes

    override fun providePresentationModel(): YesPM = YesPM(di.instance())

    @BindView(R.id.gif_yes)
    lateinit var uiGifYes: ImageView

    @BindView(R.id.refresh_yes_gif_btn)
    lateinit var uiRefreshYes: Button

    @BindView(R.id.show_no_gif)
    lateinit var uiShowNo: Button

    override fun onBindPresentationModel(pm: YesPM) {
        super.onBindPresentationModel(pm)

        uiRefreshYes.clicks() bindTo pm.refreshYesClick.consumer
        uiShowNo.clicks() bindTo pm.showNoClick.consumer

        pm.yesGif.observable.bindTo { showGif(it) }
        pm.showNo.observable.bindTo { showNo() }
    }

    private fun showGif(gifUrl: String) {
        activity?.let {
            Glide.with(it)
                    .asGif()
                    .load(gifUrl)
                    .into(uiGifYes)
        }
    }

    private fun showNo() {
        router.replaceTopController(RouterTransaction.with(NoScreen())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

}
