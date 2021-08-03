package com.bestrepositories.base_feature.utils.extensions

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setSpannable(f: (SpannableString) -> SpannableString) {
    text = f(SpannableString(text.toString()))
}

fun SpannableString.addEndDrawable(context: Context, drawableRes: Int): SpannableString {
    val newSpannable = SpannableString("$this  ")
    val imageSpan = ImageSpan(context, drawableRes)
    newSpannable.setSpan(
        imageSpan,
        newSpannable.length - 1,
        newSpannable.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return newSpannable
}