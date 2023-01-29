package com.example.kotlinconcepts.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinconcepts.db.Student
import com.example.kotlinconcepts.db.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val dao: StudentDao) : ViewModel() {

    val students = dao.getAllStudents()

    fun insertStudent(student: Student) = viewModelScope.launch {
        dao.insert(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        dao.delete(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        dao.update(student)
    }
}