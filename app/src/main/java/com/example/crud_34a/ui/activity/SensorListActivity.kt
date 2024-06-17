package com.example.crud_34a.ui.activity

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivitySensorDashBoardBinding
import com.example.crud_34a.databinding.ActivitySensorListBinding

class SensorListActivity : AppCompatActivity() {
    lateinit var sensorListBinding: ActivitySensorListBinding
    lateinit var sensorManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       sensorListBinding=ActivitySensorListBinding.inflate(layoutInflater)
        setContentView(sensorListBinding.root)

        sensorManager=getSystemService(SENSOR_SERVICE)as SensorManager

        var lstSensor=sensorManager.getSensorList(Sensor.TYPE_ALL)

        for (sensor in lstSensor){
            sensorListBinding.sensorList.append(sensor.name+"\n")
        }

    }
}