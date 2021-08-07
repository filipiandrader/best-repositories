package com.bestrepositories.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bestrepositories.data_local.base.DataConverter

@Entity(tableName = "repository")
data class RepositoryLocal(
    @PrimaryKey
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: RepositoryOwnerLocal,
    val htmlUrl: String,
    val description: String,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
    val license: RepositoryLicenseLocal,
    var like: Boolean
) {
    companion object {
        val EMPTY = RepositoryLocal(
            id = -1,
            name = "",
            fullName = "",
            owner = RepositoryOwnerLocal.EMPTY,
            htmlUrl = "",
            description = "",
            stargazersCount = -1,
            watchersCount = -1,
            language = "",
            forksCount = -1,
            license = RepositoryLicenseLocal.EMPTY,
            like = false
        )
    }
}

@Entity(tableName = "repository_owner")
data class RepositoryOwnerLocal(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String
) {
    companion object {
        val EMPTY = RepositoryOwnerLocal(
            id = -1,
            login = "",
            avatarUrl = "",
            htmlUrl = ""
        )
    }
}

@Entity(tableName = "repository_license")
data class RepositoryLicenseLocal(
    @PrimaryKey
    val key: String,
    val name: String,
    val url: String
) {
    companion object {
        val EMPTY = RepositoryLicenseLocal(
            key = "",
            name = "",
            url = ""
        )
    }
}