package com.artists

import android.app.Application
import com.artists.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Created by Mohammed MAADELLAOUI on 06/01/2022.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(mainModule)
        }
    }
}
