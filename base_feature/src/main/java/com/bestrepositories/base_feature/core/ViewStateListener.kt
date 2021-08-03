package com.bestrepositories.base_feature.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bestrepositories.base_feature.utils.extensions.observeLiveData

interface ViewStateListener {

    fun onStateLoading()

    fun hideLoading()

    fun onStateError(error: Throwable)

    fun <T> LiveData<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        observeLiveData(lifecycleOwner) {
            it.handle(onError, onLoading, onComplete, onSuccess)
        }
    }

    private fun <T> ViewState<T>.handle(
        onError: ((Throwable) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        stateHandler(
            onSuccess = {
                hideLoading()
                onSuccess?.invoke(it)
                onComplete?.invoke()
            },
            onError = { error ->
                hideLoading()
                onError?.invoke(error) ?: onStateError(error)
                onComplete?.invoke()
            },
            loading = { onLoading?.invoke() ?: onStateLoading() }
        )
    }
}