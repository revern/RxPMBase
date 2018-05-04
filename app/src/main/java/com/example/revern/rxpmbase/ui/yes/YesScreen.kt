package com.example.revern.rxpmbase.ui.yes

import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.jakewharton.rxbinding2.view.clicks
import org.kodein.di.generic.instance

class YesScreen : BaseScreen<YesPM>() {

    override val screenLayout = R.layout.screen_yes

    @BindView(R.id.gif_yes)
    lateinit var uiGifYes: ImageView

    @BindView(R.id.refresh_yes_gif_btn)
    lateinit var uiRefreshYes: Button

    @BindView(R.id.show_no_gif)
    lateinit var uiShowNo: Button

    override fun providePresentationModel(): YesPM {
        val pm: YesPM by di.instance(arg = router)
        return pm
    }

    override fun onBindPresentationModel(pm: YesPM) {
        super.onBindPresentationModel(pm)

        uiRefreshYes.clicks() bindTo pm.refreshYesClick.consumer
        uiShowNo.clicks() bindTo pm.showNoClick.consumer

        pm.yesGif.observable.bindTo { showGif(it) }
    }

    private fun showGif(gifUrl: String) {
        activity?.let {
            Glide.with(it)
                    .asGif()
                    .load(gifUrl)
                    .into(uiGifYes)
        }
    }

}
