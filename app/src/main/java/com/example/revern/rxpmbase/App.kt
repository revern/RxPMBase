package com.example.revern.rxpmbase

import android.app.Application
import com.example.revern.rxpmbase.di.initKodeinDI
import com.example.revern.rxpmbase.utils.initializeStetho

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKodeinDI(this)
        initializeStetho(this)
    }

}