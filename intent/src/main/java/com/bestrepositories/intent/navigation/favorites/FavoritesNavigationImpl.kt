package com.bestrepositories.intent.navigation.favorites

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.feature_like.navigation.FavoritesNavigation

class FavoritesNavigationImpl(private val fragment: Fragment) : FavoritesNavigation {

    override fun navigateToPrevious() {
        fragment.findNavController().popBackStack()
    }

    override fun navigateToDetail(repository: RepositoryBinding) = Unit
}