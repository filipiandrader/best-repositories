package com.bestrepositories.data_local.datasource

import com.bestrepositories.data.datasource.RepositoriesLocalDataSource
import com.bestrepositories.data_local.dao.RepositoryDao
import com.bestrepositories.data_local.mapper.RepositoriesMapper
import com.bestrepositories.data_local.model.RepositoryLocal
import com.bestrepositories.domain.model.Repository

class RepositoriesLocalDataSourceImpl(private val repositoryDao: RepositoryDao) :
    RepositoriesLocalDataSource {

    override suspend fun likeRepository(repository: Repository): Boolean {
        val repositoryLocal = RepositoriesMapper.fromDomain(repository)
        when (repository.like) {
            true -> repositoryDao.deleteRepository(repositoryLocal.apply { like = false })
            false -> repositoryDao.insertRepository(repositoryLocal.apply { like = true })
        }
        return repositoryLocal.like
    }

    override suspend fun getFavoriteRepositories() =
        RepositoriesMapper.toDomain(repositoryDao.getFavoriteRepositories().toList())

    override suspend fun getRepositoryById(id: Int) = RepositoriesMapper.toDomain(
        repositoryDao.getRepositoryById(id) ?: RepositoryLocal.EMPTY
    )
}