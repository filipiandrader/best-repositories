package com.bestrepositories.base_feature.core

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.bestrepositories.base_feature.customview.dialog.BRDialog
import com.bestrepositories.base_feature.customview.loading.BRLoading
import com.bestrepositories.base_feature.utils.extensions.hideKeyboard

abstract class BaseFragment : Fragment(), ViewStateListener {

    private val brLoading = BRLoading()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> try {
                findNavController().popBackStack()
            } catch (e: Exception) {
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    open fun addObservers(owner: LifecycleOwner) = Unit

    open fun setupView() = Unit

    fun showDialog(brDialogParams: BRDialog.Params) {
        brDialog?.dismiss()
        brDialog = BRDialog(brDialogParams).apply { show(this@BaseFragment) }
    }

    override fun onStateLoading() {
        hideLoading()
        childFragmentManager.let { brLoading.show(this) }
    }

    override fun hideLoading() {
        brLoading.dismissAllowingStateLoss()
    }

    override fun onStateError(error: Throwable) {
    }
}