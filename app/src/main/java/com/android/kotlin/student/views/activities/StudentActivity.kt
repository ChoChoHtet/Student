package com.android.kotlin.student.views.activities

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.android.kotlin.student.R
import com.android.kotlin.student.adapter.StudentAdapter
import com.android.kotlin.student.interactor.StudentInteractor
import com.android.kotlin.student.listener.StudentListener
import com.android.kotlin.student.model.Student
import com.android.kotlin.student.presenter.StudentPresenter
import com.android.kotlin.student.views.StudentView
import kotlinx.android.synthetic.main.activity_student.*
import kotlinx.android.synthetic.main.dialog_update.view.*

class StudentActivity : AppCompatActivity(), StudentView, StudentListener {
    override fun onItemClick(view: View?, data: Student,position: Int) {
       // Toast.makeText(applicationContext, "Position $position is Clicked", Toast.LENGTH_SHORT).show()
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_delete ->presenter.deleteRecord(data)
                R.id.item_view ->{
                    intent= Intent(applicationContext,StudentDetail::class.java)
                    intent.putExtra("name",data.name)
                    intent.putExtra("age",data.age)
                    startActivity(intent)
                }
                R.id.item_update -> dialog(data)
            }
            return@setOnMenuItemClickListener  true
        }
        popupMenu.show()

    }

    fun dialog(data: Student) {
       val dialogBuilder=AlertDialog.Builder(this)
        val view=this.layoutInflater.inflate(R.layout.dialog_update,null)
        dialogBuilder.setTitle("Update Dialog")
        dialogBuilder.setView(view)
        val dialog=dialogBuilder.create()
        view.dialog_update.setOnClickListener {
            val student=Student(data.id,view.dialog_name.text.toString(),view.dialog_age.text.toString())
            presenter.updateRecord(student)
            dialog.hide()

        }


        dialog.show()

    }


    override fun showDataInRecyclerView() {
        viewStudentList()
        student_name.text.clear()
        student_age.text.clear()
    }

    override fun showFailedMessage() {
        Toast.makeText(applicationContext, "Added Record Failed!!", Toast.LENGTH_SHORT).show()
    }

    override fun showToast() {
        Toast.makeText(applicationContext, "Added Record Successful!!", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage() {
        Toast.makeText(applicationContext, "Please Insert value!!", Toast.LENGTH_SHORT).show()
    }

    val presenter = StudentPresenter(this, StudentInteractor(this))
    var i: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        student_list.hasFixedSize()
        student_list.layoutManager = LinearLayoutManager(this)
        viewStudentList()
        Save.setOnClickListener {
            presenter.addRecord(i++, student_name.text.toString(), student_age.text.toString())
        }
    }

    fun viewStudentList() {
        val adapter = StudentAdapter(this)
        adapter.addStudentList(presenter.getRecord())
        adapter.notifyDataSetChanged()
        student_list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
