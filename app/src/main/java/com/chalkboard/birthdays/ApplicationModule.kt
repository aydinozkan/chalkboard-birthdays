package com.chalkboard.birthdays

import com.chalkboard.birthdays.network.di.Qualifiers
import org.koin.dsl.module

object ApplicationModule {
    fun create() = module {
        single(qualifier = Qualifiers.BASE_URL) { BuildConfig.HOST_URL }
    }
}
