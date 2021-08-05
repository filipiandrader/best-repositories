package com.bestrepositories.data_remote.core

import com.bestrepositories.data_remote.model.GenericResponse
import com.bestrepositories.data_remote.utils.constants.ConnectionException
import com.bestrepositories.data_remote.utils.constants.GenericException
import com.bestrepositories.data_remote.utils.constants.ServerException
import com.bestrepositories.data_remote.utils.constants.TimeoutException
import java.io.IOException
import java.net.SocketTimeoutException

class RequestWrapperImpl : RequestWrapper {

    @Synchronized
    override suspend fun <T> wrapper(call: suspend () -> GenericResponse<T>): GenericResponse<T> {
        return try {
            call()
        } catch (socket: SocketTimeoutException) {
            throw TimeoutException(cause = socket)
        } catch (io: IOException) {
            throw ConnectionException(cause = io)
        } catch (illegal: IllegalStateException) {
            throw ServerException(cause = illegal)
        } catch (e: Exception) {
            throw GenericException(cause = e)
        }
    }
}