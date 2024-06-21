package com.example.crud_34a.ui.activity

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity(){
    lateinit var notificationBinding: ActivityNotificationBinding
    var CHANNEL_ID = "PH"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        notificationBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(notificationBinding.root)

        notificationBinding.notficationbtn.setOnClickListener {
            showNotification()
        }
    }
    private fun showNotification() {
       var builder=NotificationCompat.Builder(this@NotificationActivity,CHANNEL_ID)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var channel=NotificationChannel(CHANNEL_ID,"My channel",NotificationManager.IMPORTANCE_DEFAULT)

            var manager:NotificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            builder.setSmallIcon(R.drawable.baseline_add_24)
            builder.setContentTitle("My notification")
            builder.setContentText("This is my notification")
            manager.notify(1,builder.build())
        }
        else{
            builder.setSmallIcon(R.drawable.baseline_add_24)
            builder.setContentTitle("My notification")
            builder.setContentText("This is my notification")
        }
        var notificationManagerCompat=NotificationManagerCompat.from(this@NotificationActivity)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManagerCompat.notify(1,builder.build())
    }
}