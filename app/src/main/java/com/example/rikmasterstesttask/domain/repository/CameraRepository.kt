package com.example.rikmasterstesttask.domain.repository

import com.example.rikmasterstesttask.domain.model.CameraDto

interface CameraRepository {
    suspend fun getCameras(): List<CameraDto>
    suspend fun getCamerasFromDb(): List<CameraDto>
    suspend fun insertAll(cameras: List<CameraDto>)
    suspend fun update(id: Long, isFavorite: Boolean)
}