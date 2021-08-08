package com.bestrepositories.data

import com.bestrepositories.data.RepositoryFactory.DUMMY_REPOSITORY
import com.bestrepositories.data.datasource.RepositoriesLocalDataSource
import com.bestrepositories.data.datasource.RepositoriesRemoteDataSource
import com.bestrepositories.data.repository.RepositoriesRepositoryImpl
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class RepositoriesRepositoryImplTest {
    @Mock
    private lateinit var dataSource: RepositoriesRemoteDataSource

    @Mock
    private lateinit var localDataSource: RepositoriesLocalDataSource

    private lateinit var repository: RepositoriesRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = RepositoriesRepositoryImpl(dataSource, localDataSource)
    }

    @Test
    fun `getRepositories WHEN has a success MUST return a list`() = runBlocking {
        stubGetRepositoriesSuccess()
        stubGetRepositoryById()
        repository.getRepositories().testFlow {
            assertEquals(listOf(DUMMY_REPOSITORY), this)
        }
    }

    @Test(expected = Throwable::class)
    fun `getRepositories WHEN has a error MUST return a throwable`() {
        stubGetRepositoriesError()
        runBlocking {
            repository.getRepositories().collect {}
        }
    }

    @Test
    fun `getFavoriteRepositories WHEN has a success MUST return a list`() = runBlocking {
        stubGetFavoriteRepositoriesSuccess()
        repository.getFavoriteRepositories().testFlow {
            assertEquals(listOf(DUMMY_REPOSITORY), this)
        }
    }

    @Test(expected = Throwable::class)
    fun `getFavoriteRepositories WHEN has a error MUST return a throwable`() = runBlocking {
        stubGetFavoriteRepositoriesError()
        runBlocking {
            repository.getFavoriteRepositories().collect {}
        }
    }

    @Test
    fun `likeRepository WHEN has a success MUST like repository`() = runBlocking {
        stubLikeRepositorySuccess(true)
        repository.likeRepository(DUMMY_REPOSITORY).testFlow {
            assertEquals(true, this)
        }
    }

    @Test
    fun `likeRepository WHEN has a success MUST dislike repository`() = runBlocking {
        stubLikeRepositorySuccess(false)
        repository.likeRepository(DUMMY_REPOSITORY).testFlow {
            assertEquals(false, this)
        }
    }

    @Test(expected = Throwable::class)
    fun `likeRepository WHEN has a error MUST return a throwable`() = runBlocking {
        stubLikeRepositoryError()
        runBlocking {
            repository.likeRepository(DUMMY_REPOSITORY).collect {}
        }
    }

    private fun stubGetRepositoriesSuccess() {
        whenever(dataSource.getRepositories()).thenReturn(flowOf(listOf(DUMMY_REPOSITORY)))
    }

    private fun stubGetRepositoriesError() {
        whenever(dataSource.getRepositories()).thenThrow(Throwable())
    }

    private suspend fun stubGetRepositoryById() {
        whenever(localDataSource.getRepositoryById(DUMMY_REPOSITORY.id)).thenReturn(DUMMY_REPOSITORY)
    }

    private suspend fun stubGetFavoriteRepositoriesSuccess() {
        whenever(localDataSource.getFavoriteRepositories()).thenReturn(listOf(DUMMY_REPOSITORY))
    }

    private suspend fun stubGetFavoriteRepositoriesError() {
        whenever(localDataSource.getFavoriteRepositories()).thenThrow(Throwable())
    }

    private suspend fun stubLikeRepositorySuccess(like: Boolean) {
        whenever(localDataSource.likeRepository(DUMMY_REPOSITORY)).thenReturn(like)
    }

    private suspend fun stubLikeRepositoryError() {
        whenever(localDataSource.likeRepository(DUMMY_REPOSITORY)).thenThrow(Throwable())
    }
}