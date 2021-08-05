package com.bestrepositories.data.repository

import com.bestrepositories.data.datasource.RepositoriesRemoteDataSource
import com.bestrepositories.domain.repository.RepositoriesRepository

class RepositoriesRepositoryImpl(private val dataSource: RepositoriesRemoteDataSource) :
    RepositoriesRepository {

    override fun getRepositories() = dataSource.getRepositories()
}