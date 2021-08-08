package com.bestrepositories.feature_main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.utils.extensions.isError
import com.bestrepositories.base_feature.utils.extensions.isNeutral
import com.bestrepositories.base_feature.utils.extensions.isSuccess
import com.bestrepositories.domain.usecase.LikeRepository
import com.bestrepositories.feature_main.RepositoryFactory.DUMMY_REPOSITORY
import com.bestrepositories.feature_main.repositories.presentation.DetailViewModel
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@KoinApiExtension
class DetailViewModelTest {
    
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel

    private val likeRepository: LikeRepository = mockk()

    private val testModule = module {
        single { likeRepository }
    }

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        viewModel = DetailViewModel()
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun `cleanValues WHEN called MUST postNeutral`() {
        viewModel.cleanValues()
        assertTrue(viewModel.likeRepositoryViewState.isNeutral())
    }

    @Test
    fun `likeRepository WHEN called MUST return success`() {
        stubLikeRepositorySuccess()
        val repository = RepositoryMapper.fromDomain(DUMMY_REPOSITORY)
        viewModel.likeRepository(repository)
        assertEquals(true, viewModel.likeRepositoryViewState.value?.data)
        assertTrue(viewModel.likeRepositoryViewState.isSuccess())
    }

    @Test
    fun `likeRepository WHEN called MUST return error`() {
        val dummyError = Throwable()
        stubLikeRepositoryError(dummyError)
        val repository = RepositoryMapper.fromDomain(DUMMY_REPOSITORY)
        viewModel.likeRepository(repository)
        assertEquals(dummyError, viewModel.likeRepositoryViewState.value?.error)
        assertTrue(viewModel.likeRepositoryViewState.isError())
    }

    private fun stubLikeRepositorySuccess() {
        every {
            likeRepository.invoke(
                params = LikeRepository.Params(DUMMY_REPOSITORY),
                onSuccess = captureLambda(),
                onError = any()
            )
        } answers { lambda<(Boolean) -> Unit>().invoke(true) }
    }

    private fun stubLikeRepositoryError(dummyError: Throwable) {
        every {
            likeRepository.invoke(
                params = any(),
                onSuccess = any(),
                onError = captureLambda()
            )
        } answers { lambda<(Throwable) -> Unit>().invoke(dummyError) }
    }
}