package com.bestrepositories.domain.usecase

import com.bestrepositories.domain.core.UseCase
import com.bestrepositories.domain.exception.MissingParamsException
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.repository.RepositoriesRepository
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LikeRepository(scope: CoroutineScope, private val repository: RepositoriesRepository) :
    UseCase<Boolean, LikeRepository.Params>(scope) {

    override fun run(params: Params?) = when (params) {
        null -> throw MissingParamsException()
        else -> repository.likeRepository(params.repository)
    }

    data class Params(val repository: Repository)
}