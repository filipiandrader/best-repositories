package com.bestrepositories.data_remote.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("full_name") val fullName: String? = null,
    @SerializedName("owner") val owner: RepositoryOwnerResponse? = null,
    @SerializedName("html_url") val htmlUrl: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("stargazers_count") val stargazersCount: Int? = null,
    @SerializedName("watchers_count") val watchersCount: Int? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("forks_count") val forksCount: Int? = null,
    @SerializedName("license") val license: RepositoryLicenseResponse? = null
)

data class RepositoryOwnerResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("login") val login: String? = null,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("html_url") val htmlUrl: String? = null
)

data class RepositoryLicenseResponse(
    @SerializedName("key") val key: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null
)
