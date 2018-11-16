package com.android.kotlin.student.presenter

import com.android.kotlin.student.interactor.StudentInteractor
import com.android.kotlin.student.model.Student
import com.android.kotlin.student.views.StudentView

class StudentPresenter(var studentView: StudentView?,val interactor:StudentInteractor) {
    fun addRecord(id:Int,name:String,age:String){
        when{
            name.trim()=="" || age.trim()=="" ->studentView?.showErrorMessage()
            else -> {
                val newStudent=Student(id,name,age)
                when{
                    interactor.addingStudent(newStudent)->{
                        studentView?.showToast()
                        studentView?.showDataInRecyclerView()
                    }
                    else -> studentView?.showFailedMessage()
                }
            }


        }

    }
    fun getRecord():ArrayList<Student>{
        return interactor.gettingStudentList()
    }
    fun deleteRecord(id:Student){
      when{
          interactor.deletingStudent(id)->studentView?.showDataInRecyclerView()
          else ->studentView?.showFailedMessage()
      }

    }

    fun updateRecord(student: Student){
        when{
            interactor.updatingStudent(student)->studentView?.showDataInRecyclerView()
            else ->studentView?.showFailedMessage()
        }
    }
    fun onDestroy(){
        studentView=null
    }
}