package com.bestrepositories.data_remote.mapper

interface DataRemoteMapper<in R, out D> {
    fun toDomain(data: R): D
    fun toDomain(data: List<R>) = data.map { toDomain(it) }
}