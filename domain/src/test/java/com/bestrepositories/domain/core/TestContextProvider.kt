package com.bestrepositories.domain.core

import com.bestrepositories.domain.core.ThreadContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ThreadContextProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}