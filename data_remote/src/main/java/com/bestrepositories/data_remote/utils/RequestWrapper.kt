package com.bestrepositories.data_remote.utils

import com.bestrepositories.data_remote.model.GenericResponse

interface RequestWrapper {

    suspend fun <T> wrapper(call: suspend () -> GenericResponse<T>): GenericResponse<T>
}