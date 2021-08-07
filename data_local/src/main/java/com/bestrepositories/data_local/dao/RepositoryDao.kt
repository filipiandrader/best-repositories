package com.bestrepositories.data_local.dao

import androidx.room.*
import com.bestrepositories.data_local.model.RepositoryLocal

@Dao
abstract class RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepository(vararg repository: RepositoryLocal)

    @Delete
    abstract fun deleteRepository(repository: RepositoryLocal)

    @Transaction
    @Query("SELECT * FROM repository ORDER BY stargazersCount DESC")
    abstract fun getFavoriteRepositories(): Array<RepositoryLocal>

    @Query("SELECT * FROM repository WHERE id = :id")
    abstract fun getRepositoryById(id: Int): RepositoryLocal?
}