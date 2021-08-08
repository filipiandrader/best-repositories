package com.bestrepositories.feature_main.repositories.presentation

import androidx.lifecycle.ViewModel
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.extensions.*
import com.bestrepositories.domain.usecase.FilterRepositories
import com.bestrepositories.domain.usecase.GetRepositories
import com.bestrepositories.domain.usecase.LikeRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class RepositoriesViewModel : ViewModel(), KoinComponent {

    private val getRepositories: GetRepositories by useCase()
    private val likeRepository: LikeRepository by useCase()
    private val filterRepositories: FilterRepositories by useCase()

    private val _getRepositoriesViewState by viewState<List<RepositoryBinding>>()
    private val _likeRepositoryViewState by viewState<Boolean>()
    private val _filterRepositoriesViewState by viewState<List<RepositoryBinding>>()

    val getRepositoriesViewState = _getRepositoriesViewState.asLiveData()
    val likeRepositoryViewState = _likeRepositoryViewState.asLiveData()
    val filterRepositoriesViewState = _filterRepositoriesViewState.asLiveData()

    private val repositories = mutableListOf<RepositoryBinding>()

    fun getRepositories() {
        _getRepositoriesViewState.postLoading()
        getRepositories(
            onSuccess = {
                repositories.clear()
                repositories.addAll(RepositoryMapper.fromDomain(it))
                _getRepositoriesViewState.postSuccess(repositories)
            },
            onError = { _getRepositoriesViewState.postError(it) }
        )
    }

    fun likeRepository(repository: RepositoryBinding) {
        likeRepository(
            params = LikeRepository.Params(RepositoryMapper.toDomain(repository)),
            onSuccess = { _likeRepositoryViewState.postSuccess(it) },
            onError = { _likeRepositoryViewState.postError(it) }
        )
    }

    fun filterRepositories(term: String) {
        filterRepositories(
            params = FilterRepositories.Params(RepositoryMapper.toDomain(repositories), term),
            onSuccess = { _filterRepositoriesViewState.postSuccess(RepositoryMapper.fromDomain(it)) },
            onError = { _filterRepositoriesViewState.postError(it) }
        )
    }

    fun cleanValues() {
        _getRepositoriesViewState.postNeutral()
        _likeRepositoryViewState.postNeutral()
        _filterRepositoriesViewState.postNeutral()
    }
}