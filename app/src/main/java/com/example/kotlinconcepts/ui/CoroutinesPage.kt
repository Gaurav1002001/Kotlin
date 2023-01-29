package com.example.kotlinconcepts.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinconcepts.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.*

class CoroutinesPage : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutinesBinding

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnDownload.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    for (i in 1..500000) {
                        Log.i("MY-TAG", "Downloading user $i in ${Thread.currentThread().name}")

                        withContext(Dispatchers.Main) {
                            tvDisplay.text = "Downloading user $i"
                        }

                        delay(100)
                    }
                }

            }

            btnCount.setOnClickListener {
                count++
                tvCount.text = count.toString()
            }
        }
    }
}