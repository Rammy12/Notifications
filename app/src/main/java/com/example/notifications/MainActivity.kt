package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        val nm=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        //this statment is only for cheak devices greater than OREO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            nm.createNotificationChannel(NotificationChannel("first","default",NotificationManager.IMPORTANCE_DEFAULT))
        }

        //simple notification
        binding.button.setOnClickListener {
            val simplenotification=NotificationCompat.Builder(this,"first")
                .setContentTitle("simple title")
                .setContentText("simple description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1,simplenotification)
        }

        //after touch the notification open new intent
        binding.button2.setOnClickListener {
            val intent=Intent()
            intent.action=Intent.ACTION_VIEW
            intent.data= Uri.parse("https://google.com")

            val pi=PendingIntent.getActivity(this,12,intent,PendingIntent.FLAG_CANCEL_CURRENT)

            val clickblenotification=NotificationCompat.Builder(this,"first")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentTitle("simple title")
                .setContentText("simple description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,clickblenotification)

        }


        //button press notification
        binding.button3.setOnClickListener {
            val intent=Intent()
            intent.action=Intent.ACTION_VIEW
            intent.data= Uri.parse("https://google.com")

            val pi=PendingIntent.getActivity(this,12,intent,PendingIntent.FLAG_CANCEL_CURRENT)

            val clickblenotification=NotificationCompat.Builder(this,"first")
                .addAction(R.drawable.ic_launcher_foreground,"click me ",pi)
                .setAutoCancel(true)
                .setContentTitle("simple title")
                .setContentText("simple description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(3,clickblenotification)

        }

    }
}