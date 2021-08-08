package com.bestrepositories.base_feature.core

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.bestrepositories.base_feature.R
import com.bestrepositories.base_feature.customview.dialog.BRDialog
import com.bestrepositories.base_feature.utils.extensions.hideKeyboard

abstract class BaseFragment : Fragment(), ViewStateListener {

    private var brDialog: BRDialog? = null

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addObservers(viewLifecycleOwner)
    }

    open fun addObservers(owner: LifecycleOwner) = Unit

    open fun setupView() = Unit

    fun showDialog(brDialogParams: BRDialog.Params) {
        brDialog?.dismiss()
        brDialog = BRDialog(brDialogParams).apply { show(this@BaseFragment) }
    }

    override fun onStateError(error: Throwable) {
        error.message?.let {
            showDialog(
                BRDialog.Params(
                    description = it,
                    positiveTextAction = getString(R.string.ok_button_text)
                )
            )
        }
    }
}