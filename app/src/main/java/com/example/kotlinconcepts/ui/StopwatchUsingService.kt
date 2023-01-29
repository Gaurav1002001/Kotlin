package com.example.kotlinconcepts.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinconcepts.StopwatchService
import com.example.kotlinconcepts.databinding.ActivityStopwatchUsingServiceBinding
import kotlin.math.roundToInt

class StopwatchUsingService : AppCompatActivity() {

    private lateinit var binding: ActivityStopwatchUsingServiceBinding
    private var isStarted = false
    private var time = 0.0
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopwatchUsingServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnStart.setOnClickListener {
                startOrStop()
            }

            btnReset.setOnClickListener {
                reset()
            }
        }

        serviceIntent = Intent(applicationContext, StopwatchService::class.java)
        registerReceiver(updatedTime, IntentFilter(StopwatchService.UPDATED_TIME))
    }

    private fun startOrStop() {
        if (isStarted)
            stop()
        else
            start()
    }

    private fun start() {
        serviceIntent.putExtra(StopwatchService.CURRENT_TIME, time)
        startService(serviceIntent)
        binding.btnStart.text = "Stop"
        isStarted = true
    }

    private fun stop() {
        stopService(serviceIntent)
        binding.btnStart.text = "Start"
        isStarted = false
    }

    private fun reset() {
        stop()
        time = 0.0
        binding.tvTimer.text = getFormattedTime(time)
    }

    private val updatedTime : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(StopwatchService.CURRENT_TIME, 0.0)
            binding.tvTimer.text = getFormattedTime(time)
        }
    }

    private fun getFormattedTime(time: Double) : String {
        val timeInInt = time.roundToInt()
        val hours = timeInInt % 86400 / 3600
        val minutes = timeInInt % 86400 % 3600 / 60
        val seconds = timeInInt % 86400 % 3600 % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}