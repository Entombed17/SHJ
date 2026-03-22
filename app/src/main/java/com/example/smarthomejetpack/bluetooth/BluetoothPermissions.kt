package com.example.smarthomejetpack.bluetooth

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

private const val PERMISSION_REQUEST_CODE = 100

class BluetoothPermissions( //Класс для проверки run-time разрешений
    private val activity: Activity,
    private val onAllGranted: () -> Unit,
    private val onAllDenied: () -> Unit,
    ) {

    private val requiredPermissions: Array<String> = arrayOf(
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT)

    fun checkPermissions(): Boolean { //функция проверки всех разрешений из массива
        return requiredPermissions.all { permission ->
            ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermissions() { //функция вывода диалогового окна
        activity.requestPermissions(requiredPermissions, PERMISSION_REQUEST_CODE)
    }

    fun resultPermissions(requestCode: Int, grantResult: IntArray) { //функция обработки результата
        if(requestCode == PERMISSION_REQUEST_CODE){
            val allGranted = grantResult.all { it == PackageManager.PERMISSION_GRANTED }
            if(allGranted){
                onAllGranted()//lambda
            } else {
                onAllDenied()//lambda
            }
        }
    }
}