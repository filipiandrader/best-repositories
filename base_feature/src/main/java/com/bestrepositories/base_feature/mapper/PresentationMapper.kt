package com.bestrepositories.base_feature.mapper

interface PresentationMapper<P, D> {

    fun toDomain(presentation: P): D
    fun toDomain(presentation: List<P>) = presentation.map { toDomain(it) }

    fun fromDomain(domain: D): P
    fun fromDomain(domain: List<D>) = domain.map { fromDomain(it) }
}