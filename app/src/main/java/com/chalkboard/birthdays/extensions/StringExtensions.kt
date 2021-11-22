package com.chalkboard.birthdays.extensions

fun String.extractInitials(): String {
    return this
        .split(' ')
        .mapNotNull { it.firstOrNull()?.toString() }
        .take(2)
        .reduce { acc, s -> acc + s }
}