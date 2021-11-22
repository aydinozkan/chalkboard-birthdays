package com.chalkboard.birthdays.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chalkboard.birthdays.extensions.launchSafe
import com.chalkboard.birthdays.list.domain.BirthdayListRepository

class BirthdayListViewModel(private val repository: BirthdayListRepository) : ViewModel() {

    private val _uiState = MutableLiveData<BirthdayListUiState>()
    val uiState: LiveData<BirthdayListUiState> = _uiState

    fun getBirthdays() {
        viewModelScope.launchSafe(::handleError) {
            if (repository.birthdaysCache.isEmpty()) {
                _uiState.value = BirthdayListUiState.Loading(true)
            }

            _uiState.value = BirthdayListUiState.GetBirthdaysSuccess(repository.getBirthdays().results)

            _uiState.value = BirthdayListUiState.Loading(false)
        }
    }

    private fun handleError() {
        _uiState.value = BirthdayListUiState.Error
        _uiState.value = BirthdayListUiState.Loading(false)
    }
}