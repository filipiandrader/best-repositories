package com.bestrepositories.feature_main.repositories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.feature_main.databinding.FragmentRepositoriesBinding

class RepositoriesFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentRepositoriesBinding::inflate)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root
}