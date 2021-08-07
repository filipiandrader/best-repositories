package com.bestrepositories.data.datasource

import com.bestrepositories.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoriesLocalDataSource {
    suspend fun likeRepository(repository: Repository): Boolean
    suspend fun getFavoriteRepositories(): List<Repository>
    suspend fun getRepositoryById(id: Int): Repository
}