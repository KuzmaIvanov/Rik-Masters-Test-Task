package com.example.rikmasterstesttask.data.network.entity

import com.google.gson.annotations.SerializedName

data class DoorResponse(
    @SerializedName("success") val success: String,
    @SerializedName("data") val doors: List<Door>
)
