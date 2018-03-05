package com.example.revern.rxpmbase

import android.app.Application
import com.example.revern.rxpmbase.di.appModule
import com.example.revern.rxpmbase.di.netModule
import com.example.revern.rxpmbase.utils.initializeStetho
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(netModule("https://yesno.wtf/api"))
    }

    override fun onCreate() {
        super.onCreate()

        initializeStetho(this)
    }

}