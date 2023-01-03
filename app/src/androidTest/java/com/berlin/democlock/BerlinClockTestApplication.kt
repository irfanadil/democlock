package com.berlin.democlock

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BerlinClockTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BerlinClockTestApplication)
            modules(listOf())
        }
    }
}