package com.chalkboard.birthdays.ui.list.presentation

import com.chalkboard.birthdays.ui.list.data.Birthday

sealed class BirthdayListUiState {
    data class Loading(val show: Boolean) : BirthdayListUiState()
    data class GetBirthdaysSuccess(val birthdays: List<Birthday>) : BirthdayListUiState()
    object Error : BirthdayListUiState()
}
