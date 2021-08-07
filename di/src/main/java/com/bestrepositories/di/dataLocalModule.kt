package com.bestrepositories.di

import com.bestrepositories.data.datasource.RepositoriesLocalDataSource
import com.bestrepositories.data_local.base.createRoomDatabase
import com.bestrepositories.data_local.base.providerRepositoryDao
import com.bestrepositories.data_local.datasource.RepositoriesLocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataLocalModule = module {

    single { createRoomDatabase(androidApplication()) }

    single { providerRepositoryDao(get()) }

    single<RepositoriesLocalDataSource> { RepositoriesLocalDataSourceImpl(get()) }
}