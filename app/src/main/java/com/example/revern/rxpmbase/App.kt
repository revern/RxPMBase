package com.example.revern.rxpmbase

import android.app.Application
import com.example.revern.rxpmbase.di.modules.appModule
import com.example.revern.rxpmbase.di.di
import com.example.revern.rxpmbase.di.modules.netModule
import com.example.revern.rxpmbase.utils.initializeStetho
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(netModule(BuildConfig.API_BASE_URL))
    }

    override fun onCreate() {
        super.onCreate()
        di = kodein
        initializeStetho(this)
    }

}