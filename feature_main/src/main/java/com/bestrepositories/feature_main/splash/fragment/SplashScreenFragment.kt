package com.bestrepositories.feature_main.splash.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.utils.delegateproperties.navDirections
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.feature_main.databinding.FragmentSplashScreenBinding
import com.bestrepositories.feature_main.splash.navigation.SplashScreenNavigation

class SplashScreenFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentSplashScreenBinding::inflate)
    private val navigation: SplashScreenNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun setupView() {
        Handler(requireActivity().mainLooper).postDelayed({
            navigation.navigateToRepositories()
        }, 3000)
    }
}