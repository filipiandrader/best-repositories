package com.bestrepositories.di

import com.bestrepositories.data.repository.RepositoriesRepositoryImpl
import com.bestrepositories.domain.repository.RepositoriesRepository
import org.koin.dsl.module

val dataModule = module {

    single<RepositoriesRepository> { RepositoriesRepositoryImpl(get(), get()) }
}