package com.bestrepositories.data.datasource

import com.bestrepositories.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoriesRemoteDataSource {
    fun getRepositories(): Flow<List<Repository>>
}