package com.bestrepositories.data_remote.core

import com.bestrepositories.data_remote.model.GenericResponse

interface RequestWrapper {

    suspend fun <T> wrapper(call: suspend () -> GenericResponse<T>): GenericResponse<T>
}