package com.chalkboard.birthdays.list.presentation

import com.chalkboard.birthdays.list.data.Birthday

sealed class BirthdayListUiState {
    data class Loading(val show: Boolean) : BirthdayListUiState()
    data class GetBirthdaysSuccess(val birthdays: List<Birthday>) : BirthdayListUiState()
    object Error : BirthdayListUiState()
}
