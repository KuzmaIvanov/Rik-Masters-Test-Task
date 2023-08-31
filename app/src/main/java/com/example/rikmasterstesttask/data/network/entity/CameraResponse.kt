package com.example.rikmasterstesttask.data.network.entity

import com.google.gson.annotations.SerializedName

data class CameraResponse(
    @SerializedName("success") val success: String,
    @SerializedName("data") val data: CameraData
)