package com.bestrepositories.domain.exception

open class DataSourceException(
    message: String,
    cause: Throwable? = null,
) : Exception(message, cause)