package com.bestrepositories.data_remote

import com.bestrepositories.data_remote.core.RequestWrapper
import com.bestrepositories.data_remote.model.GenericResponse

class RequestWrapperTest : RequestWrapper {

    override suspend fun <T> wrapper(call: suspend () -> GenericResponse<T>): GenericResponse<T> {
        return call()
    }
}
