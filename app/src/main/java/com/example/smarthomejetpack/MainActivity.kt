package com.example.smarthomejetpack

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.smarthomejetpack.bluetooth.BluetoothPermissions
import com.example.smarthomejetpack.bluetooth.REQUEST_CODE_BT

class MainActivity : ComponentActivity() {
    private val bluetoothPermissionsHelper: BluetoothPermissions by lazy {
        BluetoothPermissions(
            activity = this,
            onAllGranted = { Toast.makeText(this, "Все разрешения получены", Toast.LENGTH_LONG).show()},
            onAllDenied = { Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()}
        )
    }

    private val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        ?: throw IllegalStateException("Требуется наличие Bluetooth адаптера") //Если на устройстве нет адаптера

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!bluetoothPermissionsHelper.checkPermissions()){//применение методов класса BluetoothPermissions
            bluetoothPermissionsHelper.requestPermissions()
        }
    }

    //функция для получения результата с диалогового окна
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        bluetoothPermissionsHelper.resultPermissions(requestCode, grantResults)
    }

    //функция callback для получения результата от Activity и запуском startDiscovery()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_BT && resultCode == Activity.RESULT_OK){
            //bluetoothDeviceManager.startDiscovery()
        } else {
            //showErrorMessage()
        }
    }
}

