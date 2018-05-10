package com.example.revern.rxpmbase.ui.yes

import com.bumptech.glide.Glide
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.screen_yes.view.*
import org.kodein.di.generic.instance

class YesScreen : BaseScreen<YesPM>() {

    override val screenLayout = R.layout.screen_yes

    override fun providePresentationModel(): YesPM {
        val pm: YesPM by di.instance(arg = router)
        return pm
    }

    override fun onBindPresentationModel(pm: YesPM) {
        super.onBindPresentationModel(pm)

        view?.let {
            it.btn_refresh_yes.clicks() bindTo pm.refreshYesClick.consumer
            it.btn_show_no.clicks() bindTo pm.showNoClick.consumer
        }

        pm.yesGif.observable.bindTo { showGif(it) }
    }

    private fun showGif(gifUrl: String) {
        view?.let {
            Glide.with(it.context)
                    .asGif()
                    .load(gifUrl)
                    .into(it.gif_yes)
        }
    }

}
