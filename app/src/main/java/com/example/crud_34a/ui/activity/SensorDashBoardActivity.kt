package com.example.crud_34a.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivitySensorDashBoardBinding

class SensorDashBoardActivity : AppCompatActivity() {
    lateinit var sensorDashBoardBinding: ActivitySensorDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorDashBoardBinding= ActivitySensorDashBoardBinding.inflate(layoutInflater)
        setContentView(sensorDashBoardBinding.root)

        sensorDashBoardBinding.button2.setOnClickListener{
            startActivity(Intent(this@SensorDashBoardActivity,SensorListActivity::class.java))
        }
    }
}