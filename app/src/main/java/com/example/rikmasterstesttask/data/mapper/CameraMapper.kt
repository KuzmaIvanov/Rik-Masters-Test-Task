package com.example.rikmasterstesttask.data.mapper

import com.example.rikmasterstesttask.data.database.entity.CameraEntity
import com.example.rikmasterstesttask.data.network.entity.Camera
import com.example.rikmasterstesttask.domain.model.CameraDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraMapper @Inject constructor() {
    fun mapToDto(camera: Camera): CameraDto = with(camera) {
        CameraDto(
            id = id,
            name = name,
            snapshot = snapshot,
            roomName = roomName,
            isFavorite = isFavorite,
            hasRecord = hasRecord
        )
    }

    fun mapToDto(cameraEntity: CameraEntity): CameraDto = with(cameraEntity) {
        CameraDto(
            id = id,
            name = name,
            snapshot = snapshot,
            roomName = roomName,
            isFavorite = isFavorite,
            hasRecord = hasRecord
        )
    }

    fun mapToEntity(cameraDto: CameraDto): CameraEntity = with(cameraDto) {
        CameraEntity(
            id = id,
            name = name,
            snapshot = snapshot,
            roomName = roomName,
            isFavorite = isFavorite,
            hasRecord = hasRecord
        )
    }
}