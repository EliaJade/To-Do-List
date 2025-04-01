package com.example.to_do_list.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.to_do_list.utils.DatabaseManager

class TaskDAO(context: Context) {

    val databaseManager = DatabaseManager(context)

    fun insert(task: Task) {

        //Gets the data repository in write mode
        val db = databaseManager.writableDatabase

        //Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(Task.COLUMN_NAME_TITLE, task.title)
            put(Task.COLUMN_NAME_DONE, task.done)
        }

        try {
            val newRowId = db.insert(Task.COLUMN_NAME, null, values)
        Log.i("DATABASE", "Inserted task with id: $newRowId")
        } catch (e:Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }


    fun update(task: Task) {

        //Get the data repository in write mode
        val db = databaseManager.writableDatabase

        //Create a new map of values where column names are the keys
        val values = ContentValues().apply {
            put(Task.COLUMN_NAME_TITLE, task.title)
            put(Task.COLUMN_NAME_DONE, task.done)
        }
        try {
            val updatedRows =
                db.update(Task.COLUMN_NAME, values, "${Task.COLUMN_NAME_ID} = ${task.id}", null)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    fun delete(task: Task) {
        val db = databaseManager.writableDatabase

        try {
            val deletedRows =
                db.delete(Task.COLUMN_NAME, "${Task.COLUMN_NAME_ID} = ${task.id}", null)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    fun findById(id: Long): Task? {
        val db = databaseManager.readableDatabase
        val projection = arrayOf(
            Task.COLUMN_NAME_ID,
            Task.COLUMN_NAME_TITLE,
            Task.COLUMN_NAME_DONE
        )

        val selection = "${Task.COLUMN_NAME_ID} = $id"

        var task: Task? = null

        try {
            val cursor = db.query(
                Task.COLUMN_NAME,       //THE TABLE TO QUERY
                projection,         //THE ARRAY OF COLUMNS TO RETURN ( PASS NULL TO GET ALL)
                selection,          //THE COLUMNS FOR THE where CLAUSE
                null,   //THE VALUES FOR THE where CLAUSE
                null,       //DON'T GROUP THE ROWS
                null,        // DON'T FILTER BY ROW GROUPS
                null        // THE SORT ORDER
            )


            if (cursor.moveToNext()) {
                val itemId = cursor.getLong(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TITLE))
                val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DONE)) != 0

                task = Task(itemId, title, done)
            }

        } catch (e:Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }

        return task
    }

    fun findAll(): List<Task> {
        val db = databaseManager.readableDatabase
        val projection = arrayOf(
            Task.COLUMN_NAME_ID,
            Task.COLUMN_NAME_TITLE,
            Task.COLUMN_NAME_DONE
        )

        val taskList: MutableList<Task> = mutableListOf()

        try {
            val cursor = db.query(
                Task.COLUMN_NAME,       //THE TABLE TO QUERY
                projection,             //THE ARRAY OF COLUMNS TO RETURN ( PASS NULL TO GET ALL)
                null,          //THE COLUMNS FOR THE where CLAUSE
                null,       //THE VALUES FOR THE where CLAUSE
                null,           //DON'T GROUP THE ROWS
                null,            // DON'T FILTER BY ROW GROUPS
                null            // THE SORT ORDER
            )


            while (cursor.moveToNext()) {
                val itemId = cursor.getLong(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TITLE))
                val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DONE)) != 0

                val task = Task(itemId, title, done)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
            return taskList
        }

    }


