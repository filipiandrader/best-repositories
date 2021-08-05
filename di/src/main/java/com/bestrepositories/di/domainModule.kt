package com.bestrepositories.di

import com.bestrepositories.domain.core.ThreadContextProvider
import com.bestrepositories.domain.usecase.GetRepositories
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {

    single { ThreadContextProvider() }

    factory { (scope: CoroutineScope) -> GetRepositories(scope, get()) }
}