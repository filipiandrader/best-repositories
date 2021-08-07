package com.bestrepositories.domain.usecase

import com.bestrepositories.domain.core.UseCase
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.repository.RepositoriesRepository
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class GetFavoriteRepositories(
    scope: CoroutineScope,
    private val repository: RepositoriesRepository
) : UseCase<List<Repository>, Unit>(scope) {

    override fun run(params: Unit?) = repository.getFavoriteRepositories()
}