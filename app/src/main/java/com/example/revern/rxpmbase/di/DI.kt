package com.example.revern.rxpmbase.di

import android.app.Application
import com.example.revern.rxpmbase.BuildConfig
import com.example.revern.rxpmbase.di.modules.appModule
import com.example.revern.rxpmbase.di.modules.netModule
import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.no.NoPM
import com.example.revern.rxpmbase.ui.yes.YesPM
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

lateinit var di: Kodein

fun initKodeinDI(app: Application) {
    di = Kodein {
        import(appModule(app))
        import(netModule(BuildConfig.API_BASE_URL))
        bind<YesNoInteractor>() with singleton { YesNoInteractor(instance()) }
        bind<YesPM>() with singleton { YesPM(instance()) }
        bind<NoPM>() with singleton { NoPM(instance()) }
    }
}
