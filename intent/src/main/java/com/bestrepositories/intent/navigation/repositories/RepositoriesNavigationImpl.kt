package com.bestrepositories.intent.navigation.repositories

import androidx.fragment.app.Fragment
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.feature_main.repositories.fragment.RepositoriesFragmentDirections
import com.bestrepositories.feature_main.repositories.navigation.RepositoriesNavigation
import com.bestrepositories.intent.utils.navigate

class RepositoriesNavigationImpl(private val fragment: Fragment) : RepositoriesNavigation {

    override fun navigateToFavorite() = fragment.navigate(
        RepositoriesFragmentDirections.actionRepositoriesFragmentToFavoritesNavigation()
    )

    override fun navigateToDetail(repository: RepositoryBinding) = fragment.navigate(
        RepositoriesFragmentDirections.actionRepositoriesFragmentToDetailNavigation(repository)
    )
}