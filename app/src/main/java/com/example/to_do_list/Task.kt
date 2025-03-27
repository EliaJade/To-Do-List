package com.example.to_do_list

class Task (
    val id: Long,
    val title: String,
    var done: Boolean
){
companion object {
    const val COLUMN_NAME_ID = "id"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_DONE = "done"

    const val COLUMN_NAME = "Task"
    }
}