package com.example.kotlinconcepts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinconcepts.db.StudentDao
import com.example.kotlinconcepts.viewModel.StudentViewModel

class StudentViewModelFactory(private val dao: StudentDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java))
            return StudentViewModel(dao) as T
        else
            throw java.lang.IllegalArgumentException("Unknown view model class")
    }
}