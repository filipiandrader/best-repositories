package com.bestrepositories.domain.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

fun <R> Flow<R>.testFlow(test: R.() -> Unit) {
    runBlocking {
        collect {
            it.test()
        }
    }
}

val testModule = module {
    single<ThreadContextProvider> { TestContextProvider() }
}