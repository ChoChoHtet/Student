package com.android.kotlin.student.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.android.kotlin.student.model.Student


class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val STUDENT_TABLE = ("CREATE TABLE " + DB_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_AGE + " TEXT" + ")")
        db?.execSQL(STUDENT_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + DB_NAME)
        onCreate(db)


    }

    //save record
    fun addStudent(student: Student): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, student.id)
        values.put(KEY_NAME, student.name)
        values.put(KEY_AGE, student.age)
        val result = db.insert(DB_NAME, null, values)
        db.close()
        return (Integer.parseInt("$result") != -1)
    }

    //get all record
    fun getAllStudentRecord(): ArrayList<Student> {
        val studentList = ArrayList<Student>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $DB_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    studentList.add(
                        Student(
                            cursor.getInt(cursor.getColumnIndex(KEY_ID)), cursor.getString(
                                cursor.getColumnIndex(
                                    KEY_NAME
                                )
                            ), cursor.getString(cursor.getColumnIndex(KEY_AGE))
                        )
                    )

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return studentList
    }

    //delete record
    fun deleteRecord(student: Student): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, student.id)
        val result = db.delete(DB_NAME, "id=" +student.id , null)
        db.close()
        return (result != -1)


    }
    //update record
    fun updateRecord(student: Student):Boolean{
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(KEY_NAME,student.name)
        values.put(KEY_AGE,student.age)
        val result=db.update(DB_NAME,values,"id="+student.id,null)
        db.close()
        return (result !=-1)
    }


    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "Student"
        private val KEY_NAME = "name"
        private val KEY_AGE = "age"
        private val KEY_ID = "id"

    }
}