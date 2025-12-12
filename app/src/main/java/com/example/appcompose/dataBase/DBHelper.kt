package com.example.appcompose.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
            $ID_COL INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
            $NAME_COL TEXT,
            $PASSWORD_COL TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(name:String, password:String){
        val values = ContentValues().apply {
            put(NAME_COL,name)
            put(PASSWORD_COL,password)
        }
        writableDatabase.use { db ->
            db.insert(TABLE_NAME, null, values)
        }
    }

    fun isThereAnyone(name:String?, password:String?):Boolean{
        val cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME WHERE $NAME_COL = '"+name+"' AND $PASSWORD_COL = '"+password+"'",null)
        return cursor.count !=0
    }

    fun getCountOfUsers():Int{
        val cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME",null)
        val count = cursor.count
        cursor.close()
        return count
    }

    companion object{
        private const val DATABASE_NAME = "AppProfile"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Users"
        private const val ID_COL = "id"
        private const val NAME_COL = "username"
        private const val PASSWORD_COL = "password"

    }
}