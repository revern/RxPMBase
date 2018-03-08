package com.example.revern.rxpmbase.ui.no

import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bumptech.glide.Glide
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.example.revern.rxpmbase.ui.yes.YesScreen
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.clicks

class NoScreen : BaseScreen<NoPM>() {

    override val screenLayout = R.layout.screen_no

    override fun providePresentationModel(): NoPM = di.instance()

    @BindView(R.id.gif_no) lateinit var uiGifNo: ImageView

    @BindView(R.id.refresh_no_gif_btn) lateinit var uiRefreshNo: Button

    @OnClick(R.id.show_yes_gif) fun onClickShowNo() {
        router.pushController(RouterTransaction.with(YesScreen())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    override fun onBindPresentationModel(pm: NoPM) {
        super.onBindPresentationModel(pm)

        uiRefreshNo.clicks().bindTo(pm.refreshNoClick.consumer)

        pm.refreshNo.observable.bindTo { showGif(it) }
    }

    private fun showGif(gifUrl: String) {
        Glide.with(activity!!).asGif().load(gifUrl).into(uiGifNo)
    }

}