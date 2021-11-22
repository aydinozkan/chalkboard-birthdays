package com.chalkboard.birthdays.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.chalkboard.birthdays.shared.Given
import com.chalkboard.birthdays.shared.Then
import com.chalkboard.birthdays.shared.When
import com.chalkboard.birthdays.ui.list.data.BirthdayResponse
import com.chalkboard.birthdays.ui.list.domain.BirthdayListRepository
import com.chalkboard.birthdays.ui.list.presentation.BirthdayListUiState
import com.chalkboard.birthdays.ui.list.presentation.BirthdayListViewModel
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import java.lang.RuntimeException

class BirthdayListViewModelShould {
    private lateinit var viewModel: BirthdayListViewModel
    private lateinit var repository: BirthdayListRepository
    private lateinit var observer: Observer<BirthdayListUiState>

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `emit success ui state when repository returns response`() {
        Given {
            observer = mock()
            repository = mock()
            viewModel = BirthdayListViewModel(mock {
                on { runBlocking { getBirthdays() } } doReturn BirthdayResponse(emptyList())
            })
            viewModel.uiState.observeForever(observer)
        }

        When {
            viewModel.getBirthdays()
        }

        Then {
            verify(observer).onChanged(BirthdayListUiState.Loading(true))
            verify(observer).onChanged(BirthdayListUiState.GetBirthdaysSuccess(emptyList()))
            verify(observer).onChanged(BirthdayListUiState.Loading(false))
        }
    }

    @Test
    fun `emit error ui state when repository fails to return data`() {
        Given {
            observer = mock()
            repository = mock()
            viewModel = BirthdayListViewModel(mock {
                on { runBlocking { getBirthdays() } } doThrow(RuntimeException())
            })
            viewModel.uiState.observeForever(observer)
        }

        When {
            viewModel.getBirthdays()
        }

        Then {
            verify(observer).onChanged(BirthdayListUiState.Loading(true))
            verify(observer).onChanged(BirthdayListUiState.Error)
            verify(observer).onChanged(BirthdayListUiState.Loading(false))
        }
    }
}