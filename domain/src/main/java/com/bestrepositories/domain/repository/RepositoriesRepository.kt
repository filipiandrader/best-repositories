package com.bestrepositories.domain.repository

import com.bestrepositories.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {
    fun getRepositories(): Flow<List<Repository>>
}