package com.chalkboard.birthdays.extensions

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchSafe(
    onError: () -> Unit = {},
    doTask: suspend (scope: CoroutineScope) -> Unit
): Job =
    launch {
        try {
            doTask(this)
        } catch (e: Throwable) {
            if (e !is CancellationException) {
                onError()
            }
        }
    }
