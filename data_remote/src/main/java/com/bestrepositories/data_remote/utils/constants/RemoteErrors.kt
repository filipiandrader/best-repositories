package com.bestrepositories.data_remote.utils.constants

import com.bestrepositories.data_remote.utils.constants.ErrorMessageEnum.*
import com.bestrepositories.domain.DataSourceException

open class GenericException(
    message: String = GENERIC_ERROR.value,
    cause: Throwable? = null
) : DataSourceException(message = message, cause = cause)

class ServerException(
    message: String = GENERIC_ERROR.value,
    cause: Throwable? = null
) : DataSourceException(message = message, cause = cause)

class ConnectionException(
    message: String = INTERNET_ERROR.value,
    cause: Throwable? = null
) : DataSourceException(message = message, cause = cause)

class TimeoutException(
    message: String = TIMEOUT_ERROR.value,
    cause: Throwable? = null
) : DataSourceException(message = message, cause = cause)