package com.bestrepositories.feature_like.presentation

import androidx.lifecycle.ViewModel
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.extensions.*
import com.bestrepositories.domain.usecase.GetFavoriteRepositories
import com.bestrepositories.domain.usecase.LikeRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class FavoritesViewModel : ViewModel(), KoinComponent {

    private val getFavoriteRepositories: GetFavoriteRepositories by useCase()
    private val likeRepository: LikeRepository by useCase()

    private val _getFavoriteRepositoriesViewState by viewState<List<RepositoryBinding>>()
    private val _likeRepositoryViewState by viewState<List<RepositoryBinding>>()

    val getFavoriteRepositoriesViewState = _getFavoriteRepositoriesViewState.asLiveData()
    val likeRepositoryViewState = _likeRepositoryViewState.asLiveData()

    private val repositories = mutableListOf<RepositoryBinding>()

    fun getFavoriteRepositories() {
        _getFavoriteRepositoriesViewState.postLoading()
        getFavoriteRepositories(
            onSuccess = {
                repositories.clear()
                repositories.addAll(RepositoryMapper.fromDomain(it))
                _getFavoriteRepositoriesViewState.postSuccess(repositories)
            },
            onError = { _getFavoriteRepositoriesViewState.postError(it) }
        )
    }

    fun likeRepository(repository: RepositoryBinding) {
        likeRepository(
            params = LikeRepository.Params(RepositoryMapper.toDomain(repository)),
            onSuccess = {
                when (it) {
                    true -> repositories.add(repository)
                    false -> repositories.remove(repository)
                }
                _likeRepositoryViewState.postSuccess(repositories)
            },
            onError = { _likeRepositoryViewState.postError(it) }
        )
    }

    fun cleanValues() {
        _getFavoriteRepositoriesViewState.postNeutral()
        _likeRepositoryViewState.postNeutral()
    }
}