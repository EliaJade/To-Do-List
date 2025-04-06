package com.example.to_do_list.data

class Category(
    val id: Long,
    var title: String,
){
    companion object {
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TITLE = "title"

        const val TABLE_NAME = "Categories"
    }
}