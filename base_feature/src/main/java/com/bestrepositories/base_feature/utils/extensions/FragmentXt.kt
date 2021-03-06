package com.bestrepositories.base_feature.utils.extensions

import android.content.*
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.view.inputmethod.InputMethodManager.RESULT_UNCHANGED_SHOWN
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.bestrepositories.base_feature.R
import java.util.*

fun Fragment.hideKeyboard(forceClose: Boolean = false) {
    val inputManager =
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            ?: return
    val currentFocusedView = requireActivity().currentFocus ?: return
    if (forceClose) {
        inputManager.hideSoftInputFromWindow(currentFocusedView.windowToken, RESULT_UNCHANGED_SHOWN)
    } else {
        inputManager.hideSoftInputFromWindow(currentFocusedView.windowToken, HIDE_NOT_ALWAYS)
    }
}

fun Fragment.getDrawable(drawableId: Int) = ContextCompat.getDrawable(requireContext(), drawableId)

fun Fragment.openUrl(url: String) {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
    )
}