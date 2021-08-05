package com.bestrepositories.di

import com.bestrepositories.data.datasource.RepositoriesRemoteDataSource
import com.bestrepositories.data_remote.datasource.RepositoriesRemoteDataSourceImpl
import com.bestrepositories.data_remote.service.GitHubService
import com.bestrepositories.data_remote.core.RequestWrapper
import com.bestrepositories.data_remote.core.RequestWrapperImpl
import com.bestrepositories.data_remote.core.WebServiceFactory
import com.bestrepositories.data_remote.utils.constants.SEARCH_BASE_URL
import org.koin.dsl.bind
import org.koin.dsl.module

val dataRemoteModule = module {

    single { RequestWrapperImpl() } bind RequestWrapper::class

    single { WebServiceFactory.provideOkHttpClient() }

    single { WebServiceFactory.createWebService(get(), SEARCH_BASE_URL) as GitHubService }

    single<RepositoriesRemoteDataSource> { RepositoriesRemoteDataSourceImpl(get(), get()) }
}