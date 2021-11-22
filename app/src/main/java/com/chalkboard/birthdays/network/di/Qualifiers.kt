package com.chalkboard.birthdays.network.di

import org.koin.core.qualifier.StringQualifier

object Qualifiers {
    val BASE_URL = StringQualifier("base_url")
    val BASE_RETROFIT = StringQualifier("base_retrofit")
}
