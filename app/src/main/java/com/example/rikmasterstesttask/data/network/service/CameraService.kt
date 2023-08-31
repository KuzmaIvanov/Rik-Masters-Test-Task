package com.example.rikmasterstesttask.data.network.service

import com.example.rikmasterstesttask.data.network.entity.CameraResponse
import retrofit2.http.GET

interface CameraService {
    @GET("cameras")
    suspend fun getCameraResponse(): CameraResponse
}