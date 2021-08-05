package com.bestrepositories.feature_main.repositories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleOwner
import com.bestrepositories.base_feature.core.BaseFragment
import com.bestrepositories.base_feature.customview.dialog.BRDialog
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.feature_main.R
import com.bestrepositories.feature_main.databinding.FragmentRepositoriesBinding
import com.bestrepositories.feature_main.repositories.presentation.RepositoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentRepositoriesBinding::inflate)
    private val viewModel by viewModel<RepositoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun setupView() {
        viewModel.getRepositories()
        setupOnBackPressedCallback()
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.getRepositoriesViewState.onPostValue(owner) {

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