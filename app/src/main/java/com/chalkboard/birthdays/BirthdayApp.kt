package com.chalkboard.birthdays

import android.app.Application
import com.chalkboard.birthdays.ui.list.di.BirthdayListModule
import com.chalkboard.birthdays.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BirthdayApp : Application() {
    override fun onCreate() {
        super.onCreate()

        buildDependencyGraph()
    }

    private fun buildDependencyGraph() {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            }
            androidContext(this@BirthdayApp)
            modules(
                listOf(
                    ApplicationModule.create(),
                    NetworkModule.create(),
                    BirthdayListModule.create(),
                )
            )
        }
    }
}