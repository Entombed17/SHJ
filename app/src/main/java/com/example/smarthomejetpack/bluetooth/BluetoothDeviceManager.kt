package com.example.smarthomejetpack.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class  BluetoothDeviceManager(
    private val bluetoothAdapter: BluetoothAdapter,
    private val context: Context,
    ) {

    private val discoveredDevices = mutableListOf<BluetoothDevice>()
    private val intentFilter = IntentFilter().apply {
        addAction(BluetoothDevice.ACTION_FOUND)
        addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
    }

    private val deviceFounderReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when(intent.action){
                BluetoothDevice.ACTION_FOUND -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    device?.let { bluetoothDevice ->
                        if (!discoveredDevices.any { it.address == bluetoothDevice.address }) {
                            discoveredDevices.add(bluetoothDevice)
                        }
                    }
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    println("Поиск завершен")
                }
            }
        }

    }

    @SuppressLint("MissingPermission")//функция старта поиска устройств
    fun startDiscovery() {
        discoveredDevices.clear()
        context.registerReceiver(deviceFounderReceiver, intentFilter)
        bluetoothAdapter.startDiscovery()
    }

    @SuppressLint("MissingPermission")//функция завершения поиска устройств
    fun cancelDiscovery() {
        context.unregisterReceiver(deviceFounderReceiver)
        bluetoothAdapter.cancelDiscovery()
    }

    @SuppressLint("MissingPermission")//функция получения списка устройств
    fun getBondedDevices(): List<BluetoothDevice> {
        return bluetoothAdapter.bondedDevices.toList()
    }

    fun getDiscoveredDevices(): List<BluetoothDevice> {
        return discoveredDevices.toList()
    }

    fun getAllDevices(): List<BluetoothDevice> {
        return (getBondedDevices() + discoveredDevices).distinctBy { it.address }
    }
}
