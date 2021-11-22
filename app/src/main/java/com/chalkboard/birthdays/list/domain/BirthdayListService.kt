package com.chalkboard.birthdays.list.domain

import com.chalkboard.birthdays.list.data.BirthdayResponse
import retrofit2.http.GET

interface BirthdayListService {
    @GET("api/?results=1000&seed=chalkboard&inc=name,dob")
    suspend fun getBirthdays(): BirthdayResponse
}