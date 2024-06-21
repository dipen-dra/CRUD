package com.example.crud_34a.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivityLightSensorBinding

class LightSensor : AppCompatActivity() ,SensorEventListener{
    lateinit var lightSensorBinding: ActivityLightSensorBinding
    lateinit var sensor: Sensor
    lateinit var sensorManager: SensorManager

    override fun onPause(){
        sensorManager.unregisterListener(this)
        super.onPause()
    }

    override fun onResume(){
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lightSensorBinding=ActivityLightSensorBinding.inflate(layoutInflater)
        setContentView(lightSensorBinding.root)
        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor()){
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT).show()
            return
        }
        else{
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
    fun checkSensor():Boolean {
        var sensor=true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)==null){
            sensor=false
            return sensor
        }
        else{
            return sensor
        }
    }
    override fun onSensorChanged(event: SensorEvent?) {
        var values=event!!.values[0]
        if (values>100){
            lightSensorBinding.imagebright.setImageResource(R.drawable.light)
        }
        else{
            lightSensorBinding.imagebright.setImageResource(R.drawable.fuseoff)
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}