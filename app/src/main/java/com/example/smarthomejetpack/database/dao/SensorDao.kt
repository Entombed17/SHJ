package com.example.smarthomejetpack.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smarthomejetpack.database.entities.SensorData
import kotlinx.coroutines.flow.Flow

interface SensorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)//Сохранение нового устройства
    suspend fun insertSensorData(data: SensorData)

    @Query("SELECT * FROM sensor_data ORDER BY date DESC")
    fun getAllSensorData(): Flow<List<SensorData>>

    @Query("SELECT * FROM sensor_data WHERE date = :date")
    suspend fun getSensorDataByDate(date: Long): SensorData?
}