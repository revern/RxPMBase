package com.example.revern.rxpmbase.ui.second

import android.content.SharedPreferences
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.example.revern.rxpmbase.App
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.example.revern.rxpmbase.ui.home.HomeScreen
import com.github.salomonbrys.kodein.instance

class SecondScreen : BaseScreen<SecondPM>() {

    lateinit var prefs: SharedPreferences

    override val screenLayout = R.layout.screen_second

    override fun providePresentationModel(): SecondPM = SecondPM()

    @BindView(R.id.uiHello) lateinit var uiHello: TextView

    @OnClick(R.id.uiHello) fun onHelloClick() {
        router.pushController(RouterTransaction.with(HomeScreen())
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler()))
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        if(activity!!.application is App) {
            val app: App = activity!!.application as App
            prefs = app.kodein.instance()

            uiHello.text = prefs.getString("qwe", "123")
        }
    }
}