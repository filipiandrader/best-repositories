package com.bestrepositories.base_feature.utils.extensions

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat

fun View.setGone() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.showOrHideView(condition: Boolean, visibility: Int = View.GONE) {
    when (condition) {
        true -> setVisible()
        false -> when (visibility) {
            View.GONE -> setGone()
            else -> setInvisible()
        }
    }
}

fun getColor(context: Context, colorId: Int) = ContextCompat.getColor(context, colorId)

fun View.getDrawable(drawableId: Int) = ContextCompat.getDrawable(context, drawableId)