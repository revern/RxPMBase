package com.example.revern.rxpmbase.ui.no

import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import com.bluelinelabs.conductor.Router
import com.bumptech.glide.Glide
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.jakewharton.rxbinding2.view.clicks
import org.kodein.di.generic.factory

class NoScreen : BaseScreen<NoPM>() {

    override val screenLayout = R.layout.screen_no

    override val pmFactory: (Router) -> NoPM by di.factory()

    @BindView(R.id.gif_no)
    lateinit var uiGifNo: ImageView

    @BindView(R.id.refresh_no_gif_btn)
    lateinit var uiRefreshNo: Button

    @BindView(R.id.show_yes_gif)
    lateinit var uiShowYes: Button

    override fun onBindPresentationModel(pm: NoPM) {
        super.onBindPresentationModel(pm)

        uiRefreshNo.clicks() bindTo pm.refreshNoClick.consumer
        uiShowYes.clicks() bindTo pm.showYesClick.consumer

        pm.noGif.observable bindTo { showGif(it) }
    }

    private fun showGif(gifUrl: String) {
        activity?.let {
            Glide.with(it)
                    .asGif()
                    .load(gifUrl)
                    .into(uiGifNo)
        }
    }

}
