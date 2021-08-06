package com.bestrepositories.feature_like.navigation

import com.bestrepositories.base_feature.model.RepositoryBinding

interface FavoritesNavigation {

    fun navigateToPrevious()
    fun navigateToDetail(repository: RepositoryBinding)
}