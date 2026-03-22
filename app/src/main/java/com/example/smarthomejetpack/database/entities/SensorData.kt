package com.example.smarthomejetpack.database.entities

import android.health.connect.datatypes.units.Temperature
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensor_data")
data class SensorData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "avg_temperature")
    val avgTemperature: Float,

    @ColumnInfo(name = "avg_humidity")
    val avgHumidity: Float,

    @ColumnInfo(name = "max_temperature")
    val maxTemperature: Float,

    @ColumnInfo(name = "min_temperature")
    val minTemperature: Float,
)