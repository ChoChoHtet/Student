package com.android.kotlin.student.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.kotlin.student.R
import com.android.kotlin.student.listener.StudentListener
import com.android.kotlin.student.model.Student
import com.android.kotlin.student.viewholder.StudentHolder

class StudentAdapter(val listener: StudentListener): RecyclerView.Adapter<StudentHolder>() {


    private var dataList=ArrayList<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StudentHolder {
        return StudentHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent,false),listener,dataList)

    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.bind(dataList.get(position))

    }
    fun addStudentList(list:ArrayList<Student>){
        this.dataList=list

    }
}