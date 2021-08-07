package com.bestrepositories.base_feature.utils.extensions

import android.graphics.Typeface
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.style.StyleSpan
import androidx.core.text.toSpannable

fun CharSequence.setSpan(vararg fields: String, span: Any = StyleSpan(Typeface.BOLD)) =
    toSpannable().apply {
        fields.forEach {
            try {
                val startPosition = indexOf(it, ignoreCase = true)
                val endPosition = startPosition + it.length
                setSpan(span, startPosition, endPosition, SPAN_INCLUSIVE_INCLUSIVE)
            } catch (ignored: Exception) {
            }
        }
    }