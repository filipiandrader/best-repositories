package com.bestrepositories.base_feature.customview.base

import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.bestrepositories.base_feature.core.BaseFragment

open class BaseDialogFragment : DialogFragment(), LifecycleObserver {

    private var isLoading = false
    private var lifecycleOwner: LifecycleOwner? = null

    fun show(fragmentContainer: BaseFragment) {
        this.lifecycleOwner = fragmentContainer.apply {
            lifecycle.addObserver(this@BaseDialogFragment)
            show(
                fragmentContainer.childFragmentManager,
                this@BaseDialogFragment::class.java.simpleName
            )
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun reset() {
        dismissAllowingStateLoss()
        this.lifecycleOwner?.lifecycle?.removeObserver(this)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded || isLoading) {
            isLoading = true
            try {
                super.show(manager, tag)
            } catch (e: Exception) {
            }
        }
    }

    override fun dismissAllowingStateLoss() {
        if (isLoading) {
            isLoading = false
            super.dismissAllowingStateLoss()
        }
    }

    fun updateWindowFeature() {
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.decorView?.setBackgroundResource(android.R.color.transparent)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}