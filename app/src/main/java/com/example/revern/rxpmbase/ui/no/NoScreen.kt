package com.example.revern.rxpmbase.ui.no

import com.bumptech.glide.Glide
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.screen_no.view.*
import org.kodein.di.generic.instance

class NoScreen : BaseScreen<NoPM>() {

    override val screenLayout = R.layout.screen_no

    override fun providePresentationModel(): NoPM {
        val pm: NoPM by di.instance(arg = router)
        return pm
    }

    override fun onBindPresentationModel(pm: NoPM) {
        super.onBindPresentationModel(pm)

        view?.let {
            it.btn_refresh_no.clicks() bindTo pm.refreshNoClick.consumer
            it.btn_show_yes.clicks() bindTo pm.showYesClick.consumer
        }

        pm.noGif.observable bindTo { showGif(it) }
    }

    private fun showGif(gifUrl: String) {
        view?.let {
            Glide.with(it.context)
                    .asGif()
                    .load(gifUrl)
                    .into(it.gif_no)
        }
    }

}
