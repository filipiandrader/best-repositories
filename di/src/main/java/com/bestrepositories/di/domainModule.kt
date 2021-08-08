package com.bestrepositories.di

import com.bestrepositories.domain.core.ThreadContextProvider
import com.bestrepositories.domain.usecase.FilterRepositories
import com.bestrepositories.domain.usecase.GetFavoriteRepositories
import com.bestrepositories.domain.usecase.GetRepositories
import com.bestrepositories.domain.usecase.LikeRepository
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val domainModule = module {

    single { ThreadContextProvider() }

    factory { (scope: CoroutineScope) -> GetRepositories(scope, get()) }

    factory { (scope: CoroutineScope) -> GetFavoriteRepositories(scope, get()) }

    factory { (scope: CoroutineScope) -> LikeRepository(scope, get()) }

    factory { (scope: CoroutineScope) -> FilterRepositories(scope) }
}