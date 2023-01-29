package com.example.kotlinconcepts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinconcepts.databinding.ActivityRetrofitBinding

class RetrofitPage : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

        }
    }
}