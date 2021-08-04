package com.bestrepositories.domain

open class DataSourceException(
    message: String,
    cause: Throwable? = null,
) : Exception(message, cause)