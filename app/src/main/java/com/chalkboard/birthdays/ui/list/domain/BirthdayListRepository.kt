package com.chalkboard.birthdays.ui.list.domain

import com.chalkboard.birthdays.ui.list.data.Birthday

class BirthdayListRepository(private val service: BirthdayListService) {

    var birthdaysCache: List<Birthday> = emptyList()

    suspend fun getBirthdays() = service.getBirthdays().also {
        this.birthdaysCache = it.results
    }
}