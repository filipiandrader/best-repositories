package com.bestrepositories.data_local.base

import androidx.room.TypeConverter
import com.bestrepositories.data_local.model.RepositoryLicenseLocal
import com.bestrepositories.data_local.model.RepositoryLocal
import com.bestrepositories.data_local.model.RepositoryOwnerLocal
import com.bestrepositories.domain.model.Repository
import com.bestrepositories.domain.model.RepositoryLicense
import com.bestrepositories.domain.model.RepositoryOwner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun toOwnerLocal(string: String): RepositoryOwnerLocal {
        val type = object : TypeToken<RepositoryOwnerLocal>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromOwnerLocal(string: RepositoryOwnerLocal): String {
        return Gson().toJson(string)
    }

    @TypeConverter
    fun toLicenseLocal(string: String): RepositoryLicenseLocal {
        val type = object : TypeToken<RepositoryLicenseLocal>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromLicenseLocal(string: RepositoryLicenseLocal): String {
        return Gson().toJson(string)
    }
}