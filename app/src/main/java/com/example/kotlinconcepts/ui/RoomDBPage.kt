package com.example.kotlinconcepts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinconcepts.StudentViewModelFactory
import com.example.kotlinconcepts.adapters.StudentAdapter
import com.example.kotlinconcepts.databinding.ActivityRoomBinding
import com.example.kotlinconcepts.db.Student
import com.example.kotlinconcepts.db.StudentDB
import com.example.kotlinconcepts.viewModel.StudentViewModel

class RoomDBPage : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var adapter: StudentAdapter
    private lateinit var selectedStudent: Student
    private lateinit var viewModel: StudentViewModel

    private var isListItemClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = StudentDB.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory)[StudentViewModel::class.java]

        binding.apply {
            btnSave.setOnClickListener {
                if (isListItemClicked)
                    updateStudentData()
                else {
                    if (binding.etName.text.toString()
                            .isNotEmpty() && binding.etEmail.text.toString().isNotEmpty()
                    )
                        saveStudentData()
                }
                clearInput()
            }

            btnClear.setOnClickListener {
                if (isListItemClicked)
                    deleteStudentData()

                clearInput()
            }

            recyclerView.layoutManager = LinearLayoutManager(this@RoomDBPage)
            adapter = StudentAdapter {
                    selectedItem: Student -> listItemClicked(selectedItem)
            }
            recyclerView.adapter = adapter
            viewModel.students.observe(this@RoomDBPage) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun saveStudentData() {
        viewModel.insertStudent(
            Student(
                0,
                binding.etName.text.toString(),
                binding.etEmail.text.toString()
            )
        )
    }

    private fun updateStudentData() {
        binding.apply {
            viewModel.updateStudent(
                Student(
                    selectedStudent.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )

            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
        }
    }

    private fun deleteStudentData() {
            binding.apply {
                viewModel.deleteStudent(
                    Student(
                        selectedStudent.id,
                        etName.text.toString(),
                        etEmail.text.toString()
                    )
                )

                btnSave.text = "Save"
                btnClear.text = "Clear"
                isListItemClicked = false
            }
    }

    private fun clearInput() {
        binding.etName.setText("")
        binding.etEmail.setText("")
    }

    private fun listItemClicked(student: Student) {
//        Toast.makeText(this, "Student is ${student.name}", Toast.LENGTH_SHORT).show()

        binding.apply {
            selectedStudent = student
            btnSave.text = "Update"
            btnClear.text = "Delete"
            isListItemClicked = true
            etName.setText(selectedStudent.name)
            etEmail.setText(selectedStudent.email)
        }
    }
}