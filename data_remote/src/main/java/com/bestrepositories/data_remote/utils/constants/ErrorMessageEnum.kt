package com.bestrepositories.data_remote.utils.constants

enum class ErrorMessageEnum(val value: String) {
    INTERNET_ERROR("Tivemos um problema de conexão, tente novamente mais tarde."),
    GENERIC_ERROR("Tivemos um problema ao conectar ao servidor. Tente novamente mais tarde."),
    TIMEOUT_ERROR("Tempo da operação expirado")
}