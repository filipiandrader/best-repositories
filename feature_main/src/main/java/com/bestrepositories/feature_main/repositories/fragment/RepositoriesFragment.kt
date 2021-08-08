package com.bestrepositories.feature_main.repositories.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleOwner
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.customview.dialog.BRDialog
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.adapter.RepositoriesAdapter
import com.bestrepositories.base_feature.utils.delegateproperties.navDirections
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.base_feature.utils.extensions.setGone
import com.bestrepositories.base_feature.utils.extensions.setVisible
import com.bestrepositories.feature_main.R
import com.bestrepositories.feature_main.databinding.FragmentRepositoriesBinding
import com.bestrepositories.feature_main.repositories.navigation.RepositoriesNavigation
import com.bestrepositories.feature_main.repositories.presentation.RepositoriesViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class RepositoriesFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentRepositoriesBinding::inflate)
    private val viewModel by viewModel<RepositoriesViewModel>()
    private val navigation: RepositoriesNavigation by navDirections()

    private lateinit var adapter: RepositoriesAdapter
    private lateinit var recyclerState: Parcelable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerState = binding.repositoriesRecyclerView.layoutManager?.onSaveInstanceState()!!
        outState.putParcelable("state", recyclerState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            recyclerState = savedInstanceState.getParcelable("state")!!
        }
    }

    override fun setupView() {
        viewModel.getRepositories()
        setupOnBackPressedCallback()

        binding.repositoriesFavoritesImageView.setOnClickListener { navigation.navigateToFavorite() }
        binding.repositoriesBRToolbar.doOnSubmit(this) { viewModel.filterRepositories(it) }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.getRepositoriesViewState.onPostValue(owner,
            onLoading = { onStateLoading(true) },
            onSuccess = { fillView(it) }
        )

        viewModel.filterRepositoriesViewState.onPostValue(owner) {
            fillView(it, getString(R.string.warning_search_empty_list))
        }
    }

    private fun onStateLoading(loading: Boolean) {
        when (loading) {
            true -> {
                binding.apply {
                    repositoriesBRLoading.setVisible()
                    repositoriesGroup.setGone()
                }
            }
            false -> {
                binding.apply {
                    repositoriesBRLoading.setGone()
                    repositoriesGroup.setVisible()
                }
            }
        }
    }

    private fun fillView(
        repositories: List<RepositoryBinding>,
        message: String = getString(R.string.warning_empty_list)
    ) {
        onStateLoading(false)
        setupVisibility(repositories.isEmpty(), message)
        adapter = RepositoriesAdapter(
            clickListener = { navigation.navigateToDetail(it) },
            likeListener = { viewModel.likeRepository(it) }
        )
        adapter.items = repositories.toMutableList()
        binding.repositoriesRecyclerView.adapter = adapter
        if (::recyclerState.isInitialized) {
            binding.repositoriesRecyclerView.layoutManager?.onRestoreInstanceState(recyclerState)
        }
    }

    private fun setupVisibility(isEmpty: Boolean, message: String) {
        when (isEmpty) {
            true -> binding.apply {
                repositoriesBREmptyList.apply {
                    this.message = message
                    setVisible()
                }
                repositoriesRecyclerView.setGone()
            }
            false -> binding.apply {
                repositoriesBREmptyList.setGone()
                repositoriesRecyclerView.setVisible()
            }
        }
    }

    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            showDialog(
                BRDialog.Params(
                    description = getString(R.string.close_app_confirmation_message),
                    positiveTextAction = getString(R.string.exit_button_text),
                    negativeTextAction = getString(R.string.cancel_button_text),
                    positiveAction = { requireActivity().finish() }
                )
            )
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.cleanValues()
    }
}