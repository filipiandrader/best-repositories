package com.bestrepositories.data_remote.datasource

import com.bestrepositories.data.datasource.RepositoriesRemoteDataSource
import com.bestrepositories.data_remote.core.RequestWrapper
import com.bestrepositories.data_remote.mapper.RepositoryMapper
import com.bestrepositories.data_remote.service.GitHubService
import kotlinx.coroutines.flow.flow

class RepositoriesRemoteDataSourceImpl(
    private val webService: GitHubService, private val requestWrapper: RequestWrapper
) : RepositoriesRemoteDataSource {

    override fun getRepositories() = flow {
        emit(
            RepositoryMapper.toDomain(
                requestWrapper.wrapper {
                    webService.getRepositories()
                }.data!!
            )
        )
    }
}