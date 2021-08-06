package com.bestrepositories.feature_like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.utils.adapter.RepositoriesAdapter
import com.bestrepositories.base_feature.utils.delegateproperties.navDirections
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.feature_like.databinding.FragmentFavoritesBinding
import com.bestrepositories.feature_like.navigation.FavoritesNavigation

class FavoritesFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentFavoritesBinding::inflate)
    private val navigation: FavoritesNavigation by navDirections()

    private lateinit var adapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun setupView() {
        adapter = RepositoriesAdapter(
            clickListener = { navigation.navigateToDetail(it) },
            likeListener = { }
        )

        setupBackPressed()
    }

    private fun setupBackPressed() {
        binding.favoritesBRToolbar.setBackAction { navigation.navigateToPrevious() }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigation.navigateToPrevious()
        }
    }
}