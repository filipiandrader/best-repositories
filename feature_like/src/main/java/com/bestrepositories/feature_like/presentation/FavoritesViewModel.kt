package com.bestrepositories.feature_like.presentation

import androidx.lifecycle.ViewModel
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.extensions.*
import com.bestrepositories.domain.usecase.GetFavoriteRepositories
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class FavoritesViewModel : ViewModel(), KoinComponent {

    private val getFavoriteRepositories: GetFavoriteRepositories by useCase()

    private val _getFavoriteRepositoriesViewState by viewState<List<RepositoryBinding>>()

    val getFavoriteRepositoriesViewState = _getFavoriteRepositoriesViewState.asLiveData()

    fun getFavoriteRepositories() {
        _getFavoriteRepositoriesViewState.postLoading()
        getFavoriteRepositories(
            onSuccess = {
                _getFavoriteRepositoriesViewState.postSuccess(
                    RepositoryMapper.fromDomain(it)
                )
            },
            onError = { _getFavoriteRepositoriesViewState.postError(it) }
        )
    }

    fun cleanValues() {
        _getFavoriteRepositoriesViewState.postNeutral()
    }
}