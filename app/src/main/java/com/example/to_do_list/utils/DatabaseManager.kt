package com.example.to_do_list.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.to_do_list.data.Category
import com.example.to_do_list.data.Task

class DatabaseManager (context: Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "reminders.db"
        const val DATABASE_VERSION = 1

        //Los elementos que sale en el database que se pueden quitar y añadir
        private const val SQL_CREATE_TABLE_TASK =
            "CREATE TABLE ${Task.TABLE_NAME} (" +
                    "${Task.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Task.COLUMN_NAME_TITLE} TEXT," +
                    "${Task.COLUMN_NAME_DONE} BOOLEAN," +
                    "${Task.COLUMN_NAME_CATEGORY_ID} INTEGER)"

        private const val SQL_DROP_TABLE_TASK = "DROP TABLE IF EXISTS ${Task.TABLE_NAME}"

        private const val SQL_CREATE_TABLE_CATEGORY =
            "CREATE TABLE ${Category.TABLE_NAME} (" +
                    "${Category.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Category.COLUMN_NAME_TITLE} TEXT,)"

        private const val SQL_DROP_TABLE_CATEGORY = "DROP TABLE IF EXISTS ${Category.TABLE_NAME}"

    }

    //creación del database para poder modificarlo de manera limpia y efectivo
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_TASK)
        Log.i("DATABASE", "Created table Task")
    }

    //se usa para añadir (update) elementos al database, al hacerlo habría que cambiar el número de la version
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onDestroy(db)
        onCreate(db)
    }

    //se usa para eliminar la tabla entera del database, esto sirve mientras estamos en la fase de desarrollo de la aplicacion pero para uso de usuarios no sirve ya que puede borrar sus datos
    //ej añadir mas colunas a la tabla habria que destruir la tabla anterior

    private fun onDestroy(db: SQLiteDatabase) {
        db.execSQL(SQL_DROP_TABLE_TASK)
    }
}





