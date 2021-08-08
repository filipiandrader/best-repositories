package com.bestrepositories.domain.usecase

import com.bestrepositories.domain.core.testModule
import com.bestrepositories.domain.repository.RepositoriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@KoinApiExtension
class GetRepositoriesTest {

    @Mock
    private lateinit var repository: RepositoriesRepository
    private lateinit var subject: GetRepositories

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testModule) }
        subject = GetRepositories(CoroutineScope(Dispatchers.Unconfined), repository)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN call MUST return a repository list`() {
        subject.run()
    }
}