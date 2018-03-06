package com.example.revern.rxpmbase.ui.home

import android.content.SharedPreferences
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.example.revern.rxpmbase.R
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.ui.base.BaseScreen
import com.example.revern.rxpmbase.ui.second.SecondScreen
import com.github.salomonbrys.kodein.instance

class HomeScreen : BaseScreen<HomePM>() {

    private val prefs: SharedPreferences = di.instance()

    override val screenLayout = R.layout.screen_home

    override fun providePresentationModel(): HomePM = HomePM()

    @BindView(R.id.uiPrefsET) lateinit var uiPrefsET: EditText

    @OnClick(R.id.uiHello) fun onHelloClick() {
        prefs.edit().putString("qwe", uiPrefsET.text.toString()).apply()
        router.pushController(RouterTransaction.with(SecondScreen()).popChangeHandler(FadeChangeHandler()).pushChangeHandler(FadeChangeHandler()))
    }
}