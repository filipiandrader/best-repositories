package com.bestrepositories.data_local.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bestrepositories.data_local.dao.RepositoryDao
import com.bestrepositories.data_local.model.RepositoryLicenseLocal
import com.bestrepositories.data_local.model.RepositoryLocal
import com.bestrepositories.data_local.model.RepositoryOwnerLocal

@Database(
    entities = [RepositoryLocal::class, RepositoryOwnerLocal::class, RepositoryLicenseLocal::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class BRDatabase : RoomDatabase() {

    abstract fun repositoryDAO(): RepositoryDao
}