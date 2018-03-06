package com.example.revern.rxpmbase.di

import android.app.Application
import com.example.revern.rxpmbase.BuildConfig
import com.example.revern.rxpmbase.di.modules.appModule
import com.example.revern.rxpmbase.di.modules.netModule
import com.github.salomonbrys.kodein.Kodein

lateinit var di: Kodein

fun initKodeinDI(app: Application) {
    di = Kodein {
        import(appModule(app))
        import(netModule(BuildConfig.API_BASE_URL))
    }
}