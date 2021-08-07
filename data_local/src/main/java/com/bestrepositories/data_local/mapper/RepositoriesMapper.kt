package com.bestrepositories.data_local.mapper

import com.bestrepositories.data_local.model.RepositoryLicenseLocal
import com.bestrepositories.data_local.model.RepositoryLocal
import com.bestrepositories.data_local.model.RepositoryOwnerLocal
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.model.RepositoryLicense
import com.bestrepositories.domain.model.RepositoryOwner

object RepositoriesMapper : DataLocalMapper<RepositoryLocal, Repository> {

    override fun toDomain(local: RepositoryLocal) = Repository(
        id = local.id,
        name = local.name,
        fullName = local.fullName,
        owner = RepositoriesOwnerMapper.toDomain(local.owner),
        htmlUrl = local.htmlUrl,
        description = local.description,
        stargazersCount = local.stargazersCount,
        watchersCount = local.watchersCount,
        language = local.language,
        forksCount = local.forksCount,
        license = RepositoriesLicenseMapper.toDomain(local.license),
        like = local.like
    )

    override fun fromDomain(domain: Repository) = RepositoryLocal(
        id = domain.id,
        name = domain.name,
        fullName = domain.fullName,
        owner = RepositoriesOwnerMapper.fromDomain(domain.owner),
        htmlUrl = domain.htmlUrl,
        description = domain.description,
        stargazersCount = domain.stargazersCount,
        watchersCount = domain.watchersCount,
        language = domain.language,
        forksCount = domain.forksCount,
        license = RepositoriesLicenseMapper.fromDomain(domain.license),
        like = domain.like
    )
}

object RepositoriesOwnerMapper : DataLocalMapper<RepositoryOwnerLocal, RepositoryOwner> {

    override fun toDomain(local: RepositoryOwnerLocal) = RepositoryOwner(
        id = local.id,
        login = local.login,
        avatarUrl = local.avatarUrl,
        htmlUrl = local.htmlUrl
    )

    override fun fromDomain(domain: RepositoryOwner) = RepositoryOwnerLocal(
        id = domain.id,
        login = domain.login,
        avatarUrl = domain.avatarUrl,
        htmlUrl = domain.htmlUrl
    )
}

object RepositoriesLicenseMapper : DataLocalMapper<RepositoryLicenseLocal, RepositoryLicense> {

    override fun toDomain(local: RepositoryLicenseLocal) = RepositoryLicense(
        key = local.key,
        name = local.name,
        url = local.url
    )

    override fun fromDomain(domain: RepositoryLicense) = RepositoryLicenseLocal(
        key = domain.key,
        name = domain.name,
        url = domain.url
    )
}