package com.bestrepositories.data.repository

import com.bestrepositories.data.datasource.RepositoriesLocalDataSource
import com.bestrepositories.data.datasource.RepositoriesRemoteDataSource
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.repository.RepositoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class RepositoriesRepositoryImpl(
    private val dataSource: RepositoriesRemoteDataSource,
    private val localDataSource: RepositoriesLocalDataSource
) : RepositoriesRepository {

    override fun getRepositories() = dataSource.getRepositories()

    override fun getFavoriteRepositories() = flow {
        emit(localDataSource.getFavoriteRepositories())
    }

    override fun likeRepository(repository: Repository) = flow {
        emit(localDataSource.likeRepository(repository))
    }

    private suspend fun syncWithLocalInfo(repositories: Flow<List<Repository>>): List<Repository> {
        val repositoriesSync = mutableListOf<Repository>()
        val list = repositories.single()
        list.map {
            val repositoryLocal = localDataSource.getRepositoryById(it.id)
            if (repositoryLocal.id == -1) {
                repositoriesSync.add(it)
            } else {
                repositoriesSync.add(repositoryLocal)
            }
        }
        return repositoriesSync
    }
}