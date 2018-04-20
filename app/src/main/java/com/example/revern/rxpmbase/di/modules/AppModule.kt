package com.example.revern.rxpmbase.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.revern.rxpmbase.utils.storage.IStorage
import com.example.revern.rxpmbase.utils.storage.Storage
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun appModule(application: Application) = Kodein.Module {

    bind<Context>() with singleton { application }

    bind<Gson>() with singleton {
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    }

    bind<SharedPreferences>() with singleton {
        instance<Context>().getSharedPreferences(instance<Context>().packageName, Context.MODE_PRIVATE)
    }

    bind<IStorage>() with singleton { Storage(instance(), instance()) }

}
