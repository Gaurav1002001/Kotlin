package com.example.kotlinconcepts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinconcepts.R
import com.example.kotlinconcepts.db.Student

class StudentAdapter(private val clickListener: (Student)->Unit) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private var studentsList = ArrayList<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val student = studentsList[position]
        holder.tvName.text = student.name
        holder.tvEmail.text = student.email

        holder.itemView.setOnClickListener {
            clickListener(student)
        }
    }

    override fun getItemCount() = studentsList.size

    fun setList(students: List<Student>) {
        studentsList.clear()
        studentsList.addAll(students)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
    }
}