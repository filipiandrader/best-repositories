package com.bestrepositories.data_remote.service

import retrofit2.http.GET

interface GitHubService {

    @GET(GitHubConstants.REPOSITORIES)
    suspend fun getRepositories()
}

object GitHubConstants {
    const val REPOSITORIES = "repositories?q=language:kotlin&sort=stars"
}