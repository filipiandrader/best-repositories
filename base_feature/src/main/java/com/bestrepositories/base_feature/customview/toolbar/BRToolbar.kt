package com.bestrepositories.base_feature.customview.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.bestrepositories.base_feature.R
import com.bestrepositories.base_feature.utils.extensions.doOnSubmit
import com.bestrepositories.base_feature.utils.extensions.setGone
import com.bestrepositories.base_feature.utils.extensions.setInvisible
import com.bestrepositories.base_feature.utils.extensions.setVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class BRToolbar @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyle: Int = 0,
    private val defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val toolbarTitle: AppCompatTextView
    private val toolbarNavigationIcon: AppCompatImageView
    private val toolbarSearch: TextInputEditText
    private val toolbarSearchInputLayout: TextInputLayout

    var title: String = ""
        set(value) {
            field = value
            setupToolbarTitle()
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.br_toolbar, this, true).run {
            toolbarTitle = findViewById(R.id.toolbarTitleTextView)
            toolbarNavigationIcon = findViewById(R.id.toolbarNavigationIconImageView)
            toolbarSearch = findViewById(R.id.toolbarSearchTextInputEditText)
            toolbarSearchInputLayout = findViewById(R.id.toolbarSearchTextInputLayout)
        }
        setupStyleable()
    }

    private fun setupToolbarTitle() {
        toolbarTitle.text = title
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

                when (getBoolean(R.styleable.BRToolbar_hasBackButton, false)) {
                    true -> toolbarNavigationIcon.setVisible()
                    false -> toolbarNavigationIcon.setInvisible()
                }
                when (getBoolean(R.styleable.BRToolbar_hasSearchOption, false)) {
                    true -> toolbarSearchInputLayout.setVisible()
                    false -> toolbarSearchInputLayout.setGone()
                }
            } finally {
                recycle()
            }
        }
    }

    fun setBackAction(action: () -> Unit) {
        toolbarNavigationIcon.setOnClickListener { action() }
    }

    fun doOnSubmit(fragment: Fragment, event: (text: String) -> Unit) {
        toolbarSearch.doOnSubmit(fragment) { event(it) }
    }
}