package com.bestrepositories.feature_main.repositories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleOwner
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.utils.delegateproperties.navDirections
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.base_feature.utils.extensions.*
import com.bestrepositories.feature_main.R
import com.bestrepositories.feature_main.databinding.FragmentDetailBinding
import com.bestrepositories.feature_main.repositories.navigation.DetailNavigation
import com.bestrepositories.feature_main.repositories.presentation.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class DetailFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentDetailBinding::inflate)
    private val navigation: DetailNavigation by navDirections()
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun setupView() {
        binding.apply {
            detailRepositoryBRToolbar.title = navigation.repository.fullName

            when (navigation.repository.like) {
                true -> itemRepositoryLikeImageView.setImageDrawable(getDrawable(R.drawable.ic_heart_menu))
                false -> itemRepositoryLikeImageView.setImageDrawable(getDrawable(R.drawable.ic_heart_outline_white))
            }
            itemRepositoryLikeImageView.setOnClickListener { viewModel.likeRepository(navigation.repository) }

            detailRepositoryAvatarImageView.loadUrl(navigation.repository.owner.avatarUrl)
            detailRepositoryDescriptionTextView.text = navigation.repository.description
            detailRepositoryStarsTextView.text =
                getString(
                    R.string.repository_stars,
                    navigation.repository.stargazersCount.getFormatedNumber()
                )
                    .setSpan(navigation.repository.stargazersCount.getFormatedNumber())
            detailRepositoryForksTextView.text =
                getString(
                    R.string.repository_forks,
                    navigation.repository.forksCount.getFormatedNumber()
                )
                    .setSpan(navigation.repository.forksCount.getFormatedNumber())
            detailRepositoryWatchersTextView.text =
                getString(
                    R.string.repository_watchers,
                    navigation.repository.watchersCount.getFormatedNumber()
                )
                    .setSpan(navigation.repository.watchersCount.getFormatedNumber())
            detailRepositoryLanguageTextView.text = navigation.repository.language
            detailRepositoryLicenseTextView.text = navigation.repository.license.name
            detailRepositoryGithubTextView.setOnClickListener {
                openUrl(navigation.repository.htmlUrl)
            }
        }
        setupBackPressed()
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.likeRepositoryViewState.onPostValue(owner) {
            navigation.repository.like = it
            binding.apply {
                when (navigation.repository.like) {
                    true -> itemRepositoryLikeImageView.setImageDrawable(getDrawable(R.drawable.ic_heart_menu))
                    false -> itemRepositoryLikeImageView.setImageDrawable(getDrawable(R.drawable.ic_heart_outline_white))
                }
            }
        }
    }

    private fun setupBackPressed() {
        binding.detailRepositoryBRToolbar.setBackAction { navigation.navigateToPrevious() }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigation.navigateToPrevious()
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.cleanValues()
    }
}