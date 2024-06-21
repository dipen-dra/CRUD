package com.example.crud_34a.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivityGyroSensorBinding

class GyroSensorActivity : AppCompatActivity() , SensorEventListener {
    lateinit var gyroSensorBinding: ActivityGyroSensorBinding
    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(gyroSensorBinding.root)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if (!checkSensor()){
            return
        }
        else{
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!!
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)

        }
    }
    fun checkSensor():Boolean {
        var sensor=true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)==null){
            sensor=false
            return sensor
        }
        else{
            return sensor
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values=event!!.values[1]
        if (values>0){
            gyroSensorBinding.gyrotext.text="Right"
        }
        else if (values<0){
            gyroSensorBinding.gyrotext.text="Left"
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}