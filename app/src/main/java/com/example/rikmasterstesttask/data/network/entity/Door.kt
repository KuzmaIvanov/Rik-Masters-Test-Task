package com.example.rikmasterstesttask.data.network.entity

import com.google.gson.annotations.SerializedName

data class Door(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("room") val room: String?,
    @SerializedName("favorites") val isFavorite: Boolean,
    @SerializedName("snapshot") val snapshot: String?
)