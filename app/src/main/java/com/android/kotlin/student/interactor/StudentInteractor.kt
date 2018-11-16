package com.android.kotlin.student.interactor

import android.content.Context
import com.android.kotlin.student.database.DBHandler
import com.android.kotlin.student.model.Student

class StudentInteractor (context: Context){
    val handler=DBHandler(context)
    fun addingStudent(student: Student):Boolean {
       return handler.addStudent(student)
    }
    fun gettingStudentList():ArrayList<Student>{
        return handler.getAllStudentRecord()
    }
    fun deletingStudent(id:Student):Boolean{
        return handler.deleteRecord(id)
    }
    fun updatingStudent(student: Student):Boolean{
        return handler.updateRecord(student)
    }
}