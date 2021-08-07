package com.bestrepositories.data_local.base

import android.app.Application
import androidx.room.Room

fun createRoomDatabase(application: Application) =
    Room.databaseBuilder(application,
        BRDatabase::class.java, "bestrepositories.db")
        .build()

fun providerRepositoryDao(database: BRDatabase) = database.repositoryDAO()