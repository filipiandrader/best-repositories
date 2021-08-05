package com.bestrepositories.feature_main.repositories.presentation

import androidx.lifecycle.ViewModel
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.extensions.*
import com.bestrepositories.domain.usecase.GetRepositories
import org.koin.core.KoinComponent

class RepositoriesViewModel : ViewModel(), KoinComponent {

    private val getRepositories: GetRepositories by useCase()

    private val _getRepositoriesViewState by viewState<List<RepositoryBinding>>()
    val getRepositoriesViewState = _getRepositoriesViewState.asLiveData()

    fun getRepositories() {
        _getRepositoriesViewState.postLoading()
        getRepositories(
            onSuccess = { _getRepositoriesViewState.postSuccess(RepositoryMapper.fromDomain(it)) },
            onError = { _getRepositoriesViewState.postError(it) }
        )
    }

    fun likeRepository(repository: RepositoryBinding) {

    }

    fun cleanValues() {
        _getRepositoriesViewState.postNeutral()
    }
}