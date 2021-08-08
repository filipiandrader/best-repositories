package com.bestrepositories.data_remote

import com.bestrepositories.data_remote.RepositoryFactory.DUMMY_REPOSITORY_RESPONSE
import com.bestrepositories.data_remote.datasource.RepositoriesRemoteDataSourceImpl
import com.bestrepositories.data_remote.mapper.RepositoryMapper
import com.bestrepositories.data_remote.model.GenericResponse
import com.bestrepositories.data_remote.model.RepositoryResponse
import com.bestrepositories.data_remote.service.GitHubService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.assertEquals

@KoinApiExtension
class RepositoriesRemoteDataSourceImplTest : KoinTest {

    private lateinit var dataSource: RepositoriesRemoteDataSourceImpl
    private val webService = mock<GitHubService>()

    @Before
    fun before() {
        startKoin { modules(testModule) }
        dataSource = RepositoriesRemoteDataSourceImpl(webService)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test(expected = RuntimeException::class)
    fun `WHEN getRepositories has TimeOut MUST throw RuntimeException`() {
        runBlocking {
            whenever(webService.getRepositories()).thenThrow(RuntimeException::class.java)
            dataSource.getRepositories().first()
        }
    }

    @Test
    fun `WHEN getRepositories is called MUST return a list`() = runBlocking {
        val dummyResponse = GenericResponse(
            totalCount = 50,
            incompleteResults = false,
            data = listOf(DUMMY_REPOSITORY_RESPONSE)
        )
        stubGetRepositories(dummyResponse)
        val expectedValue = RepositoryMapper.toDomain(dummyResponse.data!!)
        dataSource.getRepositories().testFlow {
            assertEquals(expectedValue, this)
        }
    }

    private suspend fun stubGetRepositories(dummyResponse: GenericResponse<List<RepositoryResponse>>) {
        whenever(webService.getRepositories()).thenReturn(dummyResponse)
    }
}