package com.bestrepositories.feature_like

import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.model.RepositoryLicense
import com.bestrepositories.domain.model.RepositoryOwner

object FavoritesFactory {

    val DUMMY_REPOSITORY = Repository(
        id = 1132,
        name = "dummy",
        fullName = "dummy/dummy",
        owner = RepositoryOwner(
            id = 12313,
            login = "dummy",
            avatarUrl = "dummy",
            htmlUrl = "dummy"
        ),
        htmlUrl = "dummy",
        description = "dummy",
        stargazersCount = 1312,
        watchersCount = 123,
        language = "dummy",
        forksCount = 13132,
        license = RepositoryLicense(
            key = "dummy",
            name = "dummy",
            url = "dummy"
        ),
        like = false
    )
}