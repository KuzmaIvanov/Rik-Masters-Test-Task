package com.example.rikmasterstesttask.data.network.entity

import com.google.gson.annotations.SerializedName

data class CameraData(
    @SerializedName("room") val rooms: List<String>,
    @SerializedName("cameras") val cameras: List<Camera>
)