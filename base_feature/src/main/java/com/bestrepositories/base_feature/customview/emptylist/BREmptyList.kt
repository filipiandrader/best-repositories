package com.bestrepositories.base_feature.customview.emptylist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.bestrepositories.base_feature.R

class BREmptyList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val warningMessage: AppCompatTextView

    var message: String = ""
        set(value) {
            field = value
            setupWarningMessage()
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.br_empty_list, this, true).run {
            warningMessage = findViewById(R.id.brEmptyListWarningMessageTextView)
        }
    }

    private fun setupWarningMessage() {
        warningMessage.text = message
    }
}