package com.bestrepositories.feature_main.repositories.presentation

import androidx.lifecycle.ViewModel
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.extensions.*
import com.bestrepositories.domain.usecase.GetRepositories
import com.bestrepositories.domain.usecase.LikeRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class RepositoriesViewModel : ViewModel(), KoinComponent {

    private val getRepositories: GetRepositories by useCase()
    private val likeRepository: LikeRepository by useCase()

    private val _getRepositoriesViewState by viewState<List<RepositoryBinding>>()
    private val _likeRepositoryViewState by viewState<Boolean>()

    val getRepositoriesViewState = _getRepositoriesViewState.asLiveData()
    val likeRepositoryViewState = _likeRepositoryViewState.asLiveData()

    fun getRepositories() {
        _getRepositoriesViewState.postLoading()
        getRepositories(
            onSuccess = { _getRepositoriesViewState.postSuccess(RepositoryMapper.fromDomain(it)) },
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

    fun cleanValues() {
        _getRepositoriesViewState.postNeutral()
        _likeRepositoryViewState.postNeutral()
    }
}