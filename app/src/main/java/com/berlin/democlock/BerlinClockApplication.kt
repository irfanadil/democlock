package com.berlin.democlock

import android.app.Application
import com.berlin.democlock.viewmodel.BerlinClockViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BerlinClockApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, viewModelModule))
        }
    }
}

val appModule = module {
    single { BerlinClock() }
}

val viewModelModule = module {
    viewModel { BerlinClockViewModel(get()) }
}