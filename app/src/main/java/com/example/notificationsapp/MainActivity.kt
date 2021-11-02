package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    private val notificationId = 1

    lateinit var et: EditText
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et = findViewById(R.id.et)
        btn = findViewById(R.id.button)

        btn.setOnClickListener {
            val text = et.text.toString()
            if(text.isNotEmpty()){
                showNotificationMessage(text)
                et.text.clear()
            }else{
                Toast.makeText(this,"Type a Message", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun showNotificationMessage(message:String){
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

            val builder = Notification.Builder(this, channelId)
                .setContentTitle("New Notification")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
            notificationManager.notify(notificationId,builder.build())
        }else{
            val builder = Notification.Builder(this)
                .setContentTitle("New Notification")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
            notificationManager.notify(notificationId,builder.build())
        }
    }
}