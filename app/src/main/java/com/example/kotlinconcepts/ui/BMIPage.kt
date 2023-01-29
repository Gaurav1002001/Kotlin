package com.example.kotlinconcepts.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinconcepts.databinding.ActivityBmiBinding

class BMIPage : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnCalculate.setOnClickListener {
                val weightInKg = etWeight.text.toString()
                val heightInCm = etHeight.text.toString()

                if (validateValues(weightInKg, heightInCm)) {

                    val bmi =
                        (weightInKg.toFloat()) / ((heightInCm.toFloat() / 100) * (heightInCm.toFloat() / 100))
                    val formattedBMI = String.format("%.2f", bmi).toFloat()

                    resultCard.visibility = View.VISIBLE

                    val imm: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

                    displayResult(formattedBMI)
                }
            }
        }
    }

    private fun validateValues(weight: String, height: String): Boolean {
        if (weight.isEmpty()) {
            Toast.makeText(this, "Weight is Empty", Toast.LENGTH_SHORT).show()
            return false
        }

        if (height.isEmpty()) {
            Toast.makeText(this, "Height is Empty", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun displayResult(bmi: Float) {

        binding.tvBMI.text = bmi.toString()

        var category = ""
        var color = 0
        when (bmi) {
            in 16.0..16.9 -> {
                category = "Underweight"
                color = Color.CYAN
            }
            in 18.5..24.9 -> {
                category = "Normal"
                color = Color.GREEN
            }
            in 25.0..29.9 -> {
                category = "Overweight"
                color = Color.RED
            }
        }

        binding.tvCategory.text = category
        binding.tvCategory.setTextColor(color)
    }
}