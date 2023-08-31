package com.example.rikmasterstesttask.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rikmasterstesttask.data.database.entity.CameraEntity

@Dao
interface CameraDao {
    @Query("SELECT * FROM camera")
    suspend fun getCameras(): List<CameraEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cameras: List<CameraEntity>)

    @Query("UPDATE camera SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun update(id: Long, isFavorite: Boolean)
}