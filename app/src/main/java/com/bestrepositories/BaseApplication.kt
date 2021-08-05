package com.bestrepositories

import android.app.Application
import com.bestrepositories.di.*
import com.bestrepositories.di.intent.intentModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                intentModule +
                        listOf(
                            presentationModule,
                            domainModule,
                            dataModule,
                            dataRemoteModule,
                            dataLocalModule
                        )
            ).androidContext(this@BaseApplication)
        }
    }
}