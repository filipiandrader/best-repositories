package com.bestrepositories.domain.usecase

import com.bestrepositories.domain.factory.RepositoryFactory.DUMMY_REPOSITORY
import com.bestrepositories.domain.core.testFlow
import com.bestrepositories.domain.core.testModule
import com.bestrepositories.domain.exception.MissingParamsException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.assertEquals

@KoinApiExtension
class FilterRepositoriesTest {

    private lateinit var subject: FilterRepositories

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        subject = FilterRepositories(CoroutineScope(Dispatchers.Unconfined))
    }

    @After
    fun reset() {
        stopKoin()
    }

    @Test(expected = MissingParamsException::class)
    fun `WHEN params equals to null MUST throw MissingParamsException`() {
        subject.run()
    }

    @Test
    fun `WHEN send a term that is not on name or owner name list MUST return empty list`() {
        val repositories = listOf(DUMMY_REPOSITORY)
        subject.run(FilterRepositories.Params(term = "a", repositories = repositories)).testFlow {
            assertEquals(listOf(), this)
        }
    }

    @Test
    fun `WHEN has a query that is on name or code of the list of bank MUST filter the bank list according with that query`() {
        val repositories = listOf(DUMMY_REPOSITORY)
        val query = repositories.last().name
        val expected = listOf(repositories.last())
        subject.run(FilterRepositories.Params(term = query, repositories = repositories)).testFlow {
            assertEquals(expected, this)
        }
    }
}