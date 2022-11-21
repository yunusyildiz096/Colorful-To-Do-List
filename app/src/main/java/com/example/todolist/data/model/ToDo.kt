package com.example.todolist.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "toDo")
data class ToDo(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "priority") val priority: Int?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "checkColor") val checkColor: Int?,
) : Parcelable
