package com.bestrepositories.data_local.mapper

interface DataLocalMapper<L, D> {
    fun toDomain(local: L): D
    fun toDomain(local: List<L>) = local.map { toDomain(it) }

    fun fromDomain(domain: D): L
    fun fromDomain(domain: List<D>) = domain.map { fromDomain(it) }
}