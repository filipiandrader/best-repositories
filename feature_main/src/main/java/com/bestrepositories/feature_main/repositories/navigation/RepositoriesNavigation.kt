package com.bestrepositories.feature_main.repositories.navigation

import com.bestrepositories.base_feature.model.RepositoryBinding

interface RepositoriesNavigation {

    fun navigateToFavorite()
    fun navigateToDetail(repository: RepositoryBinding)
}