package com.bestrepositories.feature_main.repositories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.utils.delegateproperties.navDirections
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.feature_main.databinding.FragmentDetailBinding
import com.bestrepositories.feature_main.repositories.navigation.DetailNavigation

class DetailFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentDetailBinding::inflate)
    private val navigation: DetailNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun setupView() {
        binding.detailBRToolbar.title = navigation.repository.name
        setupBackPressed()
    }

    private fun setupBackPressed() {
        binding.detailBRToolbar.setBackAction { navigation.navigateToPrevious() }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigation.navigateToPrevious()
        }
    }
}