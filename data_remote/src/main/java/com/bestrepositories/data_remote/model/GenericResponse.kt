package com.bestrepositories.data_remote.model

import com.google.gson.annotations.SerializedName

data class GenericResponse<T>(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("incomplete_results") val incompleteResults: Boolean?,
    @SerializedName("items") val data: T?
)