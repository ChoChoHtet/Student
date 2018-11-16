package com.android.kotlin.student.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.kotlin.student.R
import kotlinx.android.synthetic.main.activity_student_detail.*

class StudentDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)
        val bundle:Bundle=intent.extras
        tv_student_name.text=bundle.getString("name")
        tv_student_age.text=bundle.getString("age")
    }
}
