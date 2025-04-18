package com.example.to_do_list.data

class Task (
    val id: Long,
    var title: String,
    var done: Boolean = false
){
companion object {
    const val COLUMN_NAME_ID = "id"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_DONE = "done"
    const val COLUMN_NAME_CATEGORY_ID = "category_id"

    const val TABLE_NAME = "Task"
    }
}