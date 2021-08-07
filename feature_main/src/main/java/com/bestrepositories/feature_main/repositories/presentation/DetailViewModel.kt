package com.bestrepositories.feature_main.repositories.presentation

import androidx.lifecycle.ViewModel
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.model.RepositoryBinding
import com.bestrepositories.base_feature.utils.extensions.*
import com.bestrepositories.domain.usecase.LikeRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class DetailViewModel : ViewModel(), KoinComponent {

    private val likeRepository: LikeRepository by useCase()

    private val _likeRepositoryViewState by viewState<Boolean>()
    val likeRepositoryViewState = _likeRepositoryViewState.asLiveData()

    fun likeRepository(repository: RepositoryBinding) {
        likeRepository(
            params = LikeRepository.Params(RepositoryMapper.toDomain(repository)),
            onSuccess = { _likeRepositoryViewState.postSuccess(it) },
            onError = { _likeRepositoryViewState.postError(it) }
        )
    }

    fun cleanValues() {
        _likeRepositoryViewState.postNeutral()
    }
}