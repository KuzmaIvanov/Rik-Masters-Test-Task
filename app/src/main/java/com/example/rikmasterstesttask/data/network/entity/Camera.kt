package com.example.rikmasterstesttask.data.network.entity

import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("snapshot") val snapshot: String,
    @SerializedName("room") val roomName: String?,
    @SerializedName("favorites") val isFavorite: Boolean,
    @SerializedName("rec") val hasRecord: Boolean
)