package com.bestrepositories.domain.usecase

import com.bestrepositories.domain.core.testModule
import com.bestrepositories.domain.exception.MissingParamsException
import com.bestrepositories.domain.factory.RepositoryFactory
import com.bestrepositories.domain.factory.RepositoryFactory.DUMMY_REPOSITORY
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
class LikeRepositoryTest {

    @Mock
    private lateinit var repository: RepositoriesRepository
    private lateinit var subject: LikeRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testModule) }
        subject = LikeRepository(CoroutineScope(Dispatchers.Unconfined), repository)
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
    fun `WHEN call MUST like repository`() {
        subject.run(params = LikeRepository.Params(DUMMY_REPOSITORY))
    }
}