package com.example.revern.rxpmbase.di

import android.app.Application
import com.bluelinelabs.conductor.Router
import com.example.revern.rxpmbase.BuildConfig
import com.example.revern.rxpmbase.di.modules.appModule
import com.example.revern.rxpmbase.di.modules.netModule
import com.example.revern.rxpmbase.model.YesNoInteractor
import com.example.revern.rxpmbase.ui.no.NoPM
import com.example.revern.rxpmbase.ui.yes.YesPM
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

lateinit var di: Kodein

fun initKodeinDI(app: Application) {
    di = Kodein {
        import(appModule(app))
        import(netModule(BuildConfig.API_BASE_URL))
        bind<YesNoInteractor>() with singleton { YesNoInteractor(instance()) }
        bind<YesPM>() with factory { router: Router -> YesPM(router, instance()) }
        bind<NoPM>() with factory { router: Router -> NoPM(router, instance()) }
    }
}
