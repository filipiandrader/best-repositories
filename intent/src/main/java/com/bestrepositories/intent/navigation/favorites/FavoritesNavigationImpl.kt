package com.bestrepositories.intent.navigation.favorites

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.feature_like.fragment.FavoritesFragmentDirections
import com.bestrepositories.feature_like.navigation.FavoritesNavigation
import com.bestrepositories.intent.utils.navigate

class FavoritesNavigationImpl(private val fragment: Fragment) : FavoritesNavigation {

    override fun navigateToPrevious() {
        fragment.findNavController().popBackStack()
    }

    override fun navigateToDetail(repository: RepositoryBinding) = fragment.navigate(
        FavoritesFragmentDirections.actionFavoritesFragmentToDetailNavigation(repository)
    )
}