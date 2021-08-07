package com.bestrepositories.feature_main.repositories.navigation

import com.bestrepositories.base_feature.model.RepositoryBinding

interface DetailNavigation {

    val repository: RepositoryBinding

    fun navigateToPrevious()
}