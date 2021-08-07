package com.bestrepositories.intent.navigation.detail

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bestrepositories.feature_main.repositories.fragment.DetailFragmentArgs
import com.bestrepositories.feature_main.repositories.navigation.DetailNavigation

class DetailNavigationImpl(private val fragment: Fragment) : DetailNavigation {

    private val arguments = fragment.navArgs<DetailFragmentArgs>().value
    override val repository = arguments.repository

    override fun navigateToPrevious() {
        fragment.findNavController().popBackStack()
    }
}