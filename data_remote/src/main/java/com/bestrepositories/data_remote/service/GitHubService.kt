package com.bestrepositories.data_remote.service

import com.bestrepositories.data_remote.model.GenericResponse
import com.bestrepositories.data_remote.model.RepositoryResponse
import com.bestrepositories.data_remote.service.GitHubConstants.REPOSITORIES
import retrofit2.http.GET

interface GitHubService {

    @GET(REPOSITORIES)
    suspend fun getRepositories(): GenericResponse<List<RepositoryResponse>>
}

object GitHubConstants {
    const val REPOSITORIES = "repositories?q=language:kotlin&sort=stars&per_page=100"
}