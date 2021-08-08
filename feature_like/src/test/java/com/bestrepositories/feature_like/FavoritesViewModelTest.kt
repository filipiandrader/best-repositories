package com.bestrepositories.feature_like

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bestrepositories.base_feature.mapper.RepositoryMapper
import com.bestrepositories.base_feature.utils.extensions.isError
import com.bestrepositories.base_feature.utils.extensions.isNeutral
import com.bestrepositories.base_feature.utils.extensions.isSuccess
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.usecase.FilterRepositories
import com.bestrepositories.domain.usecase.GetFavoriteRepositories
import com.bestrepositories.domain.usecase.LikeRepository
import com.bestrepositories.feature_like.FavoritesFactory.DUMMY_REPOSITORY
import com.bestrepositories.feature_like.presentation.FavoritesViewModel
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
class FavoritesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoritesViewModel

    private val getFavoriteRepositories: GetFavoriteRepositories = mockk()
    private val likeRepository: LikeRepository = mockk()
    private val filterRepositories: FilterRepositories = mockk()

    private val testModule = module {
        single { getFavoriteRepositories }
        single { likeRepository }
        single { filterRepositories }
    }

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        viewModel = FavoritesViewModel()
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun `cleanValues WHEN called MUST postNeutral`() {
        viewModel.cleanValues()
        assertTrue(viewModel.getFavoriteRepositoriesViewState.isNeutral())
        assertTrue(viewModel.likeRepositoryViewState.isNeutral())
        assertTrue(viewModel.filterRepositoriesViewState.isNeutral())
    }

    @Test
    fun `getRepositories WHEN called MUST return success`() {
        stubGetRepositoriesSuccess()
        viewModel.getFavoriteRepositories()
        assertEquals(
            RepositoryMapper.fromDomain(listOf(DUMMY_REPOSITORY)),
            viewModel.getFavoriteRepositoriesViewState.value?.data
        )
        assertTrue(viewModel.getFavoriteRepositoriesViewState.isSuccess())
    }

    @Test
    fun `getRepositories WHEN called MUST return error`() {
        val dummyError = Throwable()
        stubGetRepositoriesError(dummyError)
        viewModel.getFavoriteRepositories()
        assertEquals(dummyError, viewModel.getFavoriteRepositoriesViewState.value?.error)
        assertTrue(viewModel.getFavoriteRepositoriesViewState.isError())
    }

    @Test
    fun `likeRepository WHEN called MUST return success`() {
        stubLikeRepositorySuccess()
        val repository = RepositoryMapper.fromDomain(DUMMY_REPOSITORY)
        viewModel.likeRepository(repository)
        assertEquals(listOf(repository), viewModel.likeRepositoryViewState.value?.data)
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

    @Test
    fun `filterRepositories WHEN called MUST return success`() {
        `getRepositories WHEN called MUST return success`()
        stubFilterRepositoriesSuccess()
        viewModel.filterRepositories("dummy")
        val repository = RepositoryMapper.fromDomain(DUMMY_REPOSITORY)
        assertEquals(listOf(repository), viewModel.filterRepositoriesViewState.value?.data)
        assertTrue(viewModel.filterRepositoriesViewState.isSuccess())
    }

    @Test
    fun `filterRepositories WHEN called MUST return error`() {
        val dummyError = Throwable()
        stubFilterRepositoriesError(dummyError)
        viewModel.filterRepositories("dummy")
        assertEquals(dummyError, viewModel.filterRepositoriesViewState.value?.error)
        assertTrue(viewModel.filterRepositoriesViewState.isError())
    }

    private fun stubGetRepositoriesSuccess() {
        every {
            getFavoriteRepositories.invoke(
                onSuccess = captureLambda(),
                onError = any()
            )
        } answers { lambda<(List<Repository>) -> Unit>().invoke(listOf(DUMMY_REPOSITORY)) }
    }

    private fun stubGetRepositoriesError(dummyError: Throwable) {
        every {
            getFavoriteRepositories.invoke(
                onSuccess = any(),
                onError = captureLambda()
            )
        } answers { lambda<(Throwable) -> Unit>().invoke(dummyError) }
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

    private fun stubFilterRepositoriesSuccess() {
        every {
            filterRepositories.invoke(
                params = FilterRepositories.Params(listOf(DUMMY_REPOSITORY), "dummy"),
                onSuccess = captureLambda(),
                onError = any()
            )
        } answers { lambda<(List<Repository>) -> Unit>().invoke(listOf(DUMMY_REPOSITORY)) }
    }

    private fun stubFilterRepositoriesError(dummyError: Throwable) {
        every {
            filterRepositories.invoke(
                params = any(),
                onSuccess = any(),
                onError = captureLambda()
            )
        } answers { lambda<(Throwable) -> Unit>().invoke(dummyError) }
    }
}