package com.chalkboard.birthdays.ui.list.di

import com.chalkboard.birthdays.ui.list.domain.BirthdayListRepository
import com.chalkboard.birthdays.ui.list.domain.BirthdayListService
import com.chalkboard.birthdays.ui.list.presentation.BirthdayListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object BirthdayListModule {
    fun create() = module {
        viewModel { BirthdayListViewModel(get()) }

        single { BirthdayListRepository(get()) }

        single { provideService(get()) }
    }

    private fun provideService(retrofitClient: Retrofit) = retrofitClient.create(BirthdayListService::class.java)
}