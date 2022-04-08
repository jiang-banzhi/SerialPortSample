package com.junfa.serialportsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.junfa.serialportsample.databinding.ActivityMainBinding
import com.junfa.serialportsample.utils.SerialDataUtils
import com.junfa.serialportsample.utils.SerialPort
import com.junfa.serialportsample.utils.SerialPortFinder
import com.junfa.serialportsample.utils.SerialPortUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        test()
    }

    private fun test() {
        val allDevices = SerialPortFinder().allDevices
        Log.e("Receive", "allDevices==>${allDevices.toString()}")
        val allDevicesPath = SerialPortFinder().allDevicesPath
        Log.e("Receive", "allDevicesPath==>${allDevicesPath.toString()}")
        val instance = SerialPortUtil.getInstance()
        instance.setOnDataReceiveListener { buffer, size ->
            val receiveString = SerialDataUtils.ByteArrToHex(buffer);
            Log.e("Receive", "Receive==>${receiveString}")
            Log.e("Receive", "Receive==>${String(buffer)}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SerialPortUtil.getInstance().closeSerialPort()
    }
}