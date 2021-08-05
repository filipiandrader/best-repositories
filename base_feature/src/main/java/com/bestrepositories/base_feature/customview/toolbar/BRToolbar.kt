package com.bestrepositories.base_feature.customview.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.bestrepositories.base_feature.R

class BRToolbar @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyle: Int = 0,
    private val defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val toolbarTitle: AppCompatTextView

    init {
        LayoutInflater.from(context).inflate(R.layout.br_toolbar, this, true).run {
            toolbarTitle = findViewById(R.id.toolbarTitleTextView)
        }
        setupStyleable()
    }

    private fun setupStyleable() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BRToolbar,
            defStyle,
            defStyleRes
        ).apply {
            try {
                toolbarTitle.text = getString(R.styleable.BRToolbar_toolbarTitle) ?: ""
            } finally {
                recycle()
            }
        }
    }
}