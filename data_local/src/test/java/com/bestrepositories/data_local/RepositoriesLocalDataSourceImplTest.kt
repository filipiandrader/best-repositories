package com.bestrepositories.data_local

import com.bestrepositories.data_local.RepositoryFactory.DUMMY_REPOSITORY
import com.bestrepositories.data_local.RepositoryFactory.DUMMY_REPOSITORY_LOCAL
import com.bestrepositories.data_local.dao.RepositoryDao
import com.bestrepositories.data_local.datasource.RepositoriesLocalDataSourceImpl
import com.bestrepositories.data_local.mapper.RepositoriesMapper
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class RepositoriesLocalDataSourceImplTest {
    @Mock
    private lateinit var repositoryDao: RepositoryDao
    private lateinit var dataSource: RepositoriesLocalDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        dataSource = RepositoriesLocalDataSourceImpl(repositoryDao)
    }

    @Test
    fun `likeRepository WHEN call MUST like repository`() = runBlocking {
        val expectedValue = true
        stubInsertRepositorySuccess()
        assertEquals(expectedValue, dataSource.likeRepository(DUMMY_REPOSITORY))
    }

    @Test
    fun `likeRepository WHEN call MUST dislike repository`() = runBlocking {
        val expectedValue = false
        stubDeleteRepositorySuccess()
        assertEquals(expectedValue, dataSource.likeRepository(DUMMY_REPOSITORY))
    }

    @Test(expected = Exception::class)
    fun `likeRepository WHEN call MUST throw that exception`() = runBlocking {
        val dummyException = Exception()
        stubLikeRepositoryError(dummyException)
        dataSource.likeRepository(DUMMY_REPOSITORY)
        assertEquals(dummyException, Exception())
    }

    @Test
    fun `getFavoriteRepositories WHEN call MUST return a list`() = runBlocking {
        stubGetFavoritesRepositoriesSuccess()
        assertEquals(listOf(DUMMY_REPOSITORY), dataSource.getFavoriteRepositories())
    }

    @Test(expected = Exception::class)
    fun `getFavoriteRepositories WHEN call MUST throw that exception`() = runBlocking {
        val dummyException = Exception()
        stubGetFavoritesRepositoriesError(dummyException)
        dataSource.getFavoriteRepositories()
        assertEquals(dummyException, Exception())
    }

    @Test
    fun `getRepositoryById WHEN call MUST return a repository`() = runBlocking {
        stubGetRepositoryByIDSuccess()
        assertEquals(DUMMY_REPOSITORY, dataSource.getRepositoryById(DUMMY_REPOSITORY.id))
    }

    @Test(expected = Exception::class)
    fun `getRepositoryById WHEN call MUST throw that exception`() = runBlocking {
        val dummyException = Exception()
        stubGetRepositoryByIDError(dummyException)
        dataSource.getRepositoryById(DUMMY_REPOSITORY.id)
        assertEquals(dummyException, Exception())
    }

    private fun stubDeleteRepositorySuccess() =
        doNothing().whenever(repositoryDao)
            .deleteRepository(RepositoriesMapper.fromDomain(DUMMY_REPOSITORY.apply {
                this.like = true
            }))

    private fun stubInsertRepositorySuccess() =
        doNothing().whenever(repositoryDao)
            .insertRepository(RepositoriesMapper.fromDomain(DUMMY_REPOSITORY))

    private suspend fun stubLikeRepositoryError(dummyException: Exception) =
        doThrow(dummyException).whenever(dataSource).likeRepository(DUMMY_REPOSITORY)

    private fun stubGetFavoritesRepositoriesSuccess() =
        doReturn(arrayOf(DUMMY_REPOSITORY_LOCAL)).whenever(repositoryDao).getFavoriteRepositories()

    private suspend fun stubGetFavoritesRepositoriesError(dummyException: Exception) =
        doThrow(dummyException).whenever(dataSource).getFavoriteRepositories()

    private fun stubGetRepositoryByIDSuccess() =
        doReturn(DUMMY_REPOSITORY_LOCAL).whenever(repositoryDao)
            .getRepositoryById(DUMMY_REPOSITORY_LOCAL.id)

    private suspend fun stubGetRepositoryByIDError(dummyException: Exception) =
        doThrow(dummyException).whenever(dataSource).getRepositoryById(DUMMY_REPOSITORY_LOCAL.id)
}