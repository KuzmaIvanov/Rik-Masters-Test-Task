package com.example.rikmasterstesttask.data.network.service

import com.example.rikmasterstesttask.data.network.entity.DoorResponse
import retrofit2.http.GET

interface DoorService {
    @GET("doors")
    suspend fun getDoorResponse(): DoorResponse
}