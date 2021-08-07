package com.bestrepositories.feature_like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleOwner
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.adapter.RepositoriesAdapter
import com.bestrepositories.base_feature.utils.delegateproperties.navDirections
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.feature_like.databinding.FragmentFavoritesBinding
import com.bestrepositories.feature_like.navigation.FavoritesNavigation
import com.bestrepositories.feature_like.presentation.FavoritesViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class FavoritesFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentFavoritesBinding::inflate)
    private val navigation: FavoritesNavigation by navDirections()
    private val viewModel by viewModel<FavoritesViewModel>()

    private lateinit var adapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun setupView() {
        viewModel.getFavoriteRepositories()
        setupBackPressed()
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.getFavoriteRepositoriesViewState.onPostValue(owner) {
            fillView(it)
        }
    }

    private fun fillView(repositories: List<RepositoryBinding>) {
        adapter = RepositoriesAdapter(
            clickListener = { navigation.navigateToDetail(it) },
            likeListener = { }
        )
        adapter.items = repositories.toMutableList()
        binding.favoritesRecyclerView.adapter = adapter
    }

    private fun setupBackPressed() {
        binding.favoritesBRToolbar.setBackAction { navigation.navigateToPrevious() }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigation.navigateToPrevious()
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.cleanValues()
    }
}