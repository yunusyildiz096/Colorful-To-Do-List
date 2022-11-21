package com.example.todolist.domain.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.model.ToDo

interface ToDoListRepository {
    suspend fun save(toDo: ToDo)
    suspend fun update(toDo: ToDo)
    suspend fun delete(toDo: ToDo)
    suspend fun deleteAll()
    fun getToDoList(): List<ToDo>
    fun getToDoListPriority(priority: Int): List<ToDo>
    fun searchToDo(query: String): LiveData<List<ToDo>>
}