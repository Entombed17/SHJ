package com.example.smarthomejetpack.database.repository

import com.example.smarthomejetpack.database.dao.DeviceDao
import kotlinx.coroutines.flow.Flow

class DeviceRepository(
    private val deviceDao: DeviceDao
    ) {

    fun getAllDevices(): Flow<List<SavedDevice>> {
        return deviceDao.getAllDevices()
    }

    suspend fun saveDevice(device: SavedDevice) {
        deviceDao.saveDevice(device)
    }

    suspend fun isDeviceSaved(macAddress: String): Boolean {
        return deviceDao.isDeviceSaved(macAddress)
    }

    suspend fun getDeviceByMac(macAddress: String): SavedDevice? {
        return deviceDao.getDeviceByMac(macAddress)
    }

    suspend fun deleteDevice(device: SavedDevice) {
        deviceDao.deleteDevice(device)
    }

    suspend fun updateDeviceAlias(macAddress: String, newAlias: String) {
        val device: SavedDevice? = deviceDao.getDeviceByMac(macAddress)
        device?.let {
            val updatedDevice: SavedDevice = it.copy(customAlias = newAlias)
            deviceDao.updateDevice(updatedDevice)
        }
    }

    suspend fun updateConnectionCount(macAddress: String){
        val device: SavedDevice? = deviceDao.getDeviceByMac(macAddress)
        device?.let {
            val updateDevice: SavedDevice = it.copy(
                connectionCount = it.connectionCount + 1,
                lastConnected = System.currentTimeMillis()
            )
            deviceDao.updateDevice(updateDevice)
        }
    }
}