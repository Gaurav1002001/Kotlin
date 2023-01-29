package com.example.kotlinconcepts.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinconcepts.databinding.ActivitySignUpBinding
import com.example.kotlinconcepts.viewModel.SignUpViewModel

class SignUpPage : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private lateinit var viewModel: SignUpViewModel

    private val channelId = "com.example.kotlinConcepts.ui.channelId"
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("APP-SP", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel("Demo Channel", "This is demo channel")

        binding.apply {

            btnBmi.setOnClickListener {
                val intent = Intent(this@SignUpPage, BMIPage::class.java)
                startActivity(intent)
            }

            btnMedia.setOnClickListener {
                val intent = Intent(this@SignUpPage, MediaPage::class.java)
                startActivity(intent)
            }

            btnCoroutines.setOnClickListener {
                val intent = Intent(this@SignUpPage, CoroutinesPage::class.java)
                startActivity(intent)
            }

            btnRoomdb.setOnClickListener {
                val intent = Intent(this@SignUpPage, RoomDBPage::class.java)
                startActivity(intent)
            }

            btnServices.setOnClickListener {
                val intent = Intent(this@SignUpPage, StopwatchUsingService::class.java)
                startActivity(intent)
            }

            btnNotification.setOnClickListener {
                displayNotification()
            }

            btnCount.setOnClickListener {
                viewModel.updateCount()
                tvCount.text = viewModel.count.toString()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        editor.apply {
            putString("name", binding.etName.text.toString())
            putString("email", binding.etEmail.text.toString())
            commit()
        }
    }

    override fun onResume() {
        super.onResume()

        binding.etName.setText(sharedPreferences.getString("name", ""))
        binding.etEmail.setText(sharedPreferences.getString("email", ""))
    }

    private fun createNotificationChannel(name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun displayNotification() {
        val notification = NotificationCompat.Builder(this@SignUpPage, channelId)
            .setContentTitle("Demo Title")
            .setContentText("This is demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager?.notify(45, notification)
    }
}