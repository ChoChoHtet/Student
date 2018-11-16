package com.android.kotlin.student.listener

import android.view.View
import com.android.kotlin.student.model.Student

interface StudentListener {
    fun onItemClick(view: View?, student:Student,position:Int)
}