package com.bestrepositories.feature_like.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleOwner
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.adapter.FavoritesAdapter
import com.bestrepositories.base_feature.utils.delegateproperties.navDirections
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.base_feature.utils.extensions.setGone
import com.bestrepositories.base_feature.utils.extensions.setVisible
import com.bestrepositories.feature_like.R
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

    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerState: Parcelable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerState = binding.favoritesRecyclerView.layoutManager?.onSaveInstanceState()!!
        outState.putParcelable("state", recyclerState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            recyclerState = savedInstanceState.getParcelable("state")!!
        }
    }

    override fun setupView() {
        viewModel.getFavoriteRepositories()
        setupBackPressed()

        binding.favoritesBRToolbar.doOnSubmit(this) { viewModel.filterRepositories(it) }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.getFavoriteRepositoriesViewState.onPostValue(owner) {
            fillView(it)
        }

        viewModel.likeRepositoryViewState.onPostValue(owner) {
            fillView(it)
        }

        viewModel.filterRepositoriesViewState.onPostValue(owner) {
            fillView(it, getString(R.string.warning_search_empty_list))
        }
    }

    private fun fillView(
        repositories: List<RepositoryBinding>,
        message: String = getString(R.string.warning_empty_list)
    ) {
        setupVisibility(repositories.isEmpty(), message)
        adapter = FavoritesAdapter(
            clickListener = { navigation.navigateToDetail(it) },
            likeListener = { viewModel.likeRepository(it) }
        )
        adapter.items = repositories.toMutableList()
        binding.favoritesRecyclerView.adapter = adapter
        if (::recyclerState.isInitialized) {
            binding.favoritesRecyclerView.layoutManager?.onRestoreInstanceState(recyclerState)
        }
    }

    private fun setupVisibility(isEmpty: Boolean, message: String) {
        when (isEmpty) {
            true -> binding.apply {
                favoritesBREmptyList.apply {
                    this.message = message
                    setVisible()
                }
                favoritesRecyclerView.setGone()
            }
            false -> binding.apply {
                favoritesBREmptyList.setGone()
                favoritesRecyclerView.setVisible()
            }
        }
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