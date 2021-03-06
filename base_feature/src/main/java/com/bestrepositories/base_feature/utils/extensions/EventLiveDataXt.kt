@file:Suppress("UNCHECKED_CAST")

package com.bestrepositories.base_feature.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.bestrepositories.base_feature.core.ViewState
import com.bestrepositories.base_feature.core.ViewState.Status.ERROR
import com.bestrepositories.base_feature.core.ViewState.Status.SUCCESS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class EventLiveData<T> : MutableLiveData<T>() {

    private var hasBeenHandled = false

    private fun getContentIfNotHandled() =
        if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            value
        }

    fun getContent(isSingleEvent: Boolean) = if (isSingleEvent) getContentIfNotHandled() else value
}

fun <T> Flow<T>.asLiveData(): LiveData<ViewState<out T>> = liveData {
    try {
        collect { emit(ViewState(SUCCESS, it, null)) }
    } catch (e: Exception) {
        emit(ViewState(ERROR, null, e))
    }
}

fun <T> Flow<T>.onCollect(onSuccess: (T) -> Unit = {}, onError: (Throwable) -> Unit = {}) {
    liveData<T> {
        try {
            collect { onSuccess(it) }
        } catch (e: Exception) {
            onError(e)
        }
    }
}

suspend fun <T> Flow<T>.suspendCollect(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit = {}) {
    try {
        collect { onSuccess(it) }
    } catch (e: Exception) {
        onError(e)
    }
}

fun <T> Flow<T>.asMutableLiveData() = asLiveData() as MutableLiveData<ViewState<T>>

fun <T> Result<T>.collect(onFailure: (Throwable) -> Unit = {}, onSuccess: (T) -> Unit) {
    val success = getOrNull()

    if (success != null) {
        onSuccess(success)
        return
    }

    val failure = exceptionOrNull()
    if (failure != null) {
        onFailure(failure)
    }
}