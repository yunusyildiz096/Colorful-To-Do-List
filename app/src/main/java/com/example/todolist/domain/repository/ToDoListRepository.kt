package com.example.todolist.domain.repository

import com.example.todolist.data.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoListRepository {
    suspend fun save(toDo: ToDo)
    suspend fun update(toDo: ToDo)
    suspend fun delete(toDo: ToDo)
    suspend fun deleteAll()
    fun getToDoList(): Flow<List<ToDo>>
    fun getToDoListPriority(priority: Int): Flow<List<ToDo>>
    fun searchToDo(query: String): List<ToDo>
}