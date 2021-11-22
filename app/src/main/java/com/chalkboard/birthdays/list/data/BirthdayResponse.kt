package com.chalkboard.birthdays.list.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Keep
data class BirthdayResponse(@SerializedName("results") val results: List<Birthday>)

@Keep
@Parcelize
data class Birthday(
    @SerializedName("name") val name: BirthdayName,
    @SerializedName("dob") val dateOfBirth: BirthdayDateOfBirth
) : Parcelable

@Keep
@Parcelize
data class BirthdayName(
    @SerializedName("title") val title: String?,
    @SerializedName("first") val first: String?,
    @SerializedName("last") val last: String?
) : Parcelable {
    val nameSurname: String
        get() = "$first $last"
}

@Keep
@Parcelize
data class BirthdayDateOfBirth(
    @SerializedName("date") val date: Date,
    @SerializedName("age") val age: Int
) : Parcelable