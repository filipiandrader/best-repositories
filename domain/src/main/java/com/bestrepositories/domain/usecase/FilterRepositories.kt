package com.bestrepositories.domain.usecase

import com.bestrepositories.domain.core.UseCase
import com.bestrepositories.domain.exception.MissingParamsException
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.utils.unAccent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class FilterRepositories(scope: CoroutineScope) :
    UseCase<List<Repository>, FilterRepositories.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.term.isEmpty() -> flowOf(params.repositories)
        else -> flowOf(
            (filterByOwnerName(params) + filterByRepositoryName(params)).distinct()
                .sortedByDescending { it.stargazersCount })
    }

    private fun filterByOwnerName(params: Params) = params.repositories.filter {
        it.owner.login.unAccent().contains(params.term.unAccent(), true)
    }

    private fun filterByRepositoryName(params: Params) = params.repositories.filter {
        it.name.unAccent().contains(params.term.unAccent(), true)
    }

    data class Params(val repositories: List<Repository>, val term: String)
}