package com.example.crud_34a.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivityAccelerometerBinding
import kotlin.math.sqrt

class AccelerometerActivity : AppCompatActivity(), SensorEventListener {
    lateinit var accelerometerBinding: ActivityAccelerometerBinding
    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    private var lastShakeTime:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accelerometerBinding = ActivityAccelerometerBinding.inflate(layoutInflater)
        setContentView(accelerometerBinding.root)
        enableEdgeToEdge()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor()){
            return
        }
        else{
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)


        }


    }

    fun checkSensor():Boolean {
        var sensor=true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)==null){
            sensor=false
            return sensor
        }
        else{
            return sensor
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var values=event!!.values
        var xAxis=values[0]
        var yAxis=values[1]
        var zAxis=values[2]

        accelerometerBinding.value.text=
            "x-axis:$xAxis y-axis:$yAxis z-axis:$zAxis"

        detectShake(xAxis,yAxis,zAxis)

    }

    private fun detectShake(x: Float, y: Float, z: Float) {
        val accelerationMagnitude = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val shakeThreshold = 12.0f
        val currentTime = System.currentTimeMillis()
        if (accelerationMagnitude > shakeThreshold) {
            if (currentTime - lastShakeTime > 500) { // 500 ms delay to prevent multiple triggers
                lastShakeTime = currentTime
                showAlert()
            }
        }
    }
    private fun showAlert() {
        AlertDialog.Builder(this)
            .setTitle("Shake Detected")
            .setMessage("You have shaken the device!")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}