package com.bestrepositories.base_feature.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment

fun EditText.doOnSubmit(fragment: Fragment, submit: (query: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            submit(s.toString())
        }
    })

    setOnEditorActionListener { textView, _, _ ->
        submit(textView.text.toString())
        fragment.hideKeyboard()
        clearFocus()
        true
    }
}