package com.bestrepositories.base_feature.customview.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bestrepositories.base_feature.customview.base.BaseFullScreenDialog
import com.bestrepositories.base_feature.databinding.BrLoadingBinding
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding

class BRLoading : BaseFullScreenDialog() {

    private val binding by viewInflateBinding(BrLoadingBinding::inflate)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun onStart() {
        super.onStart()
        dialog?.window?.run {
            dialog?.setCancelable(false)
            setBackgroundDrawableResource(android.R.color.transparent)
            attributes = attributes.run {
                dimAmount = 0f
                this
            }
        }
    }
}