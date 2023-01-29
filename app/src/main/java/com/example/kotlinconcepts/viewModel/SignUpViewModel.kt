package com.example.kotlinconcepts.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

//    var count = 0
//
//    fun updateCount() {
//        count++
//    }

    var count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    fun updateCount() {
        count.value = count.value?.plus(1)
    }

}