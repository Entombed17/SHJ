package com.example.smarthomejetpack.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent

class BluetoothAdapterManager( //класс для работы с Bluetooth-адаптером
    private val activity: Activity,
    private val bluetoothAdapter: BluetoothAdapter,
    ) {

    @SuppressLint("MissingPermission")//функция проверки включен ли Bluetooth
    fun checkEnableBluetooth() {
        if(!bluetoothAdapter.isEnabled){
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            activity.startActivityForResult(intent, REQUEST_CODE_BT)
        }
    }
}