package com.bestrepositories.domain.model

data class Repository(
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: RepositoryOwner,
    val htmlUrl: String,
    val description: String,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
    val license: RepositoryLicense
)

data class RepositoryOwner(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String
)

data class RepositoryLicense(
    val key: String,
    val name: String,
    val url: String
)
