package com.chalkboard.birthdays.list.domain

import com.chalkboard.birthdays.list.data.Birthday

class BirthdayListRepository(private val service: BirthdayListService) {

    var birthdaysCache: List<Birthday> = emptyList()

    suspend fun getBirthdays() = service.getBirthdays().also {
        this.birthdaysCache = it.results
    }
}