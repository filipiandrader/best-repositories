package com.bestrepositories.data_remote.mapper

import com.bestrepositories.data_remote.model.RepositoryLicenseResponse
import com.bestrepositories.data_remote.model.RepositoryOwnerResponse
import com.bestrepositories.data_remote.model.RepositoryResponse
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.model.RepositoryLicense
import com.bestrepositories.domain.model.RepositoryOwner

object RepositoryMapper : DataRemoteMapper<RepositoryResponse, Repository> {

    override fun toDomain(data: RepositoryResponse) = Repository(
        id = data.id ?: -1,
        name = data.name ?: "",
        fullName = data.fullName ?: "",
        owner = RepositoryOwnerMapper.toDomain(data.owner ?: RepositoryOwnerResponse()),
        htmlUrl = data.htmlUrl ?: "",
        description = data.description ?: "",
        stargazersCount = data.stargazersCount ?: 0,
        watchersCount = data.watchersCount ?: 0,
        language = data.language ?: "",
        forksCount = data.forksCount ?: 0,
        license = RepositoryLicenseMapper.toDomain(data.license ?: RepositoryLicenseResponse())
    )
}

object RepositoryOwnerMapper : DataRemoteMapper<RepositoryOwnerResponse, RepositoryOwner> {

    override fun toDomain(data: RepositoryOwnerResponse) = RepositoryOwner(
        id = data.id ?: -1,
        login = data.login ?: "",
        avatarUrl = data.avatarUrl ?: "",
        htmlUrl = data.htmlUrl ?: ""
    )
}

object RepositoryLicenseMapper : DataRemoteMapper<RepositoryLicenseResponse, RepositoryLicense> {

    override fun toDomain(data: RepositoryLicenseResponse) = RepositoryLicense(
        key = data.key ?: "",
        name = data.name ?: "",
        url = data.url ?: ""
    )
}