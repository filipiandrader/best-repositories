package com.bestrepositories.data_remote

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