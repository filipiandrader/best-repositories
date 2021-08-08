package com.bestrepositories.data_remote

import com.bestrepositories.data_remote.core.RequestWrapper
import org.koin.dsl.bind
import org.koin.dsl.module

val testRequestWrapper = RequestWrapperTest()

val testModule = module {
    single { testRequestWrapper } bind RequestWrapper::class
}
