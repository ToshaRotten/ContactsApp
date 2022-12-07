package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    }
    companion object {
        const val DATABASE_NAME = "tododb"
        const val TABLE_NAME = "todos"
        //table attributes
        const val KEY_ID = "id"
        const val KEY_FNAME = "fname"
        const val KEY_LNAME = "lname"
        const val KEY_PHONE = "phone"
        const val KEY_DOB = "dayofbirthday"
    }
    val contact = Contact()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_NAME (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_FNAME TEXT NOT NULL,
                $KEY_LNAME TEXT NOT NULL,
                $PHONE TEXT NOT NULL,
                $KEY_DOB TEXT NOT NULL
            )""")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAll(): List<Todo> {
        val result = mutableListOf<Todo>()
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, null, null,
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)
            val titleIndex: Int = cursor.getColumnIndex(KEY_TITLE)
            val isDoneIndex: Int = cursor.getColumnIndex(KEY_IS_DONE)
            do {
                val todo = Todo(
                    cursor.getLong(idIndex),
                    cursor.getString(titleIndex),
                    cursor.getInt(isDoneIndex) == 1
                )
                result.add(todo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return result
    }

    fun getById(id: Long): Todo? {
        var result: Todo? = null
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, "$KEY_ID = ?", arrayOf(id.toString()),
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)
            val titleIndex: Int = cursor.getColumnIndex(KEY_TITLE)
            val isDoneIndex: Int = cursor.getColumnIndex(KEY_IS_DONE)
            result = Todo(
                cursor.getLong(idIndex),
                cursor.getString(titleIndex),
                cursor.getInt(isDoneIndex) == 1
            )
        }
        cursor.close()
        return result
    }

    fun add(title: String, isDone: Boolean = false): Long {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, title)
        contentValues.put(KEY_IS_DONE, if (isDone) 1 else 0)
        val id = database.insert(TABLE_NAME, null, contentValues)
        close()
        return id
    }

    fun update(id: Long, title: String, isDone: Boolean) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, title)
        contentValues.put(KEY_IS_DONE, if (isDone) 1 else 0)
        database.update(TABLE_NAME, contentValues, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }

    fun remove(id: Long) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }
}