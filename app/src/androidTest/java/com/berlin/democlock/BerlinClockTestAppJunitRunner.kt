package com.berlin.democlock

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import android.content.Context

class BerlinClockTestAppJunitRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, BerlinClockTestApplication::class.java.name, context)
    }
}