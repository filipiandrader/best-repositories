package com.bestrepositories.base_feature.customview.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.bestrepositories.base_feature.R

open class BaseFullScreenDialog : BaseDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BR_Fullscreen_Theme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDialogStyle()
    }

    private fun setupDialogStyle() {
        dialog?.window?.setLayout(MATCH_PARENT, MATCH_PARENT)
    }
}