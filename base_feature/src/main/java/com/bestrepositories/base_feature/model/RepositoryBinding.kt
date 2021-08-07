package com.bestrepositories.base_feature.model

import java.io.Serializable

data class RepositoryBinding(
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: RepositoryOwnerBinding,
    val htmlUrl: String,
    val description: String,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
    val license: RepositoryLicenseBinding,
    val like: Boolean
) : Serializable

data class RepositoryOwnerBinding(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String
) : Serializable

data class RepositoryLicenseBinding(
    val key: String,
    val name: String,
    val url: String
) : Serializable