package com.bestrepositories.base_feature.mapper

import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.model.RepositoryLicenseBinding
import com.bestrepositories.base_feature.model.RepositoryOwnerBinding
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.model.RepositoryLicense
import com.bestrepositories.domain.model.RepositoryOwner

object RepositoryMapper : PresentationMapper<RepositoryBinding, Repository> {

    override fun toDomain(presentation: RepositoryBinding) = Repository(
        id = presentation.id,
        name = presentation.name,
        fullName = presentation.fullName,
        owner = RepositoryOwnerMapper.toDomain(presentation.owner),
        htmlUrl = presentation.htmlUrl,
        description = presentation.description,
        stargazersCount = presentation.stargazersCount,
        watchersCount = presentation.watchersCount,
        language = presentation.language,
        forksCount = presentation.forksCount,
        license = RepositoryLicenseMapper.toDomain(presentation.license)
    )

    override fun fromDomain(domain: Repository) = RepositoryBinding(
        id = domain.id,
        name = domain.name,
        fullName = domain.fullName,
        owner = RepositoryOwnerMapper.fromDomain(domain.owner),
        htmlUrl = domain.htmlUrl,
        description = domain.description,
        stargazersCount = domain.stargazersCount,
        watchersCount = domain.watchersCount,
        language = domain.language,
        forksCount = domain.forksCount,
        license = RepositoryLicenseMapper.fromDomain(domain.license)
    )
}

object RepositoryOwnerMapper : PresentationMapper<RepositoryOwnerBinding, RepositoryOwner> {

    override fun toDomain(presentation: RepositoryOwnerBinding) = RepositoryOwner(
        id = presentation.id,
        login = presentation.login,
        avatarUrl = presentation.avatarUrl,
        htmlUrl = presentation.htmlUrl
    )

    override fun fromDomain(domain: RepositoryOwner) = RepositoryOwnerBinding(
        id = domain.id,
        login = domain.login,
        avatarUrl = domain.avatarUrl,
        htmlUrl = domain.htmlUrl
    )
}

object RepositoryLicenseMapper : PresentationMapper<RepositoryLicenseBinding, RepositoryLicense> {

    override fun toDomain(presentation: RepositoryLicenseBinding) = RepositoryLicense(
        key = presentation.key,
        name = presentation.name,
        url = presentation.url
    )

    override fun fromDomain(domain: RepositoryLicense) = RepositoryLicenseBinding(
        key = domain.key,
        name = domain.name,
        url = domain.url
    )
}