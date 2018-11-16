package com.android.kotlin.student.viewholder

import android.view.View
import com.android.kotlin.student.baseviewholder.BaseVH
import com.android.kotlin.student.listener.StudentListener
import com.android.kotlin.student.model.Student
import kotlinx.android.synthetic.main.student_item.view.*

class StudentHolder(val view: View,val listener:StudentListener,val data:ArrayList<Student>) : BaseVH<Student>(view) ,View.OnClickListener{
    override fun onClick(view: View?) {
        val position=adapterPosition
        if(position<0)return
        val student=data[position]as Student
        listener.onItemClick(view,student,position)

    }

    val name=view.tv_student_name
    val age=view.tv_student_age
    override fun bind(data: Student) {
        name.text=data.name
        age.text=data.age
        view.setOnClickListener(this)
    }
}