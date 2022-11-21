package com.example.todolist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "mill") val mill : Long?
)
