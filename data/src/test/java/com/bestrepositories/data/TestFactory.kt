package com.bestrepositories.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun <R> Flow<R>.testFlow(test: R.() -> Unit) {
    runBlocking {
        collect {
            it.test()
        }
    }
}

fun <T> catchError(call: () -> Flow<T>, onError: Throwable.() -> Unit) = runBlocking {
    try {
        call().collect {
        }
    } catch (e: Throwable) {
        onError(e)
    }
}