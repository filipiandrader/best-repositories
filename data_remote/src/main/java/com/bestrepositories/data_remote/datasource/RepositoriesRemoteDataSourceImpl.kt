package com.bestrepositories.data_remote.datasource

import com.bestrepositories.data.datasource.RepositoriesRemoteDataSource
import com.bestrepositories.data_remote.core.RequestWrapper
import com.bestrepositories.data_remote.mapper.RepositoryMapper
import com.bestrepositories.data_remote.service.GitHubService
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class RepositoriesRemoteDataSourceImpl(private val webService: GitHubService) :
    RepositoriesRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

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