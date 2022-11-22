package com.example.todolist.data.repository

import com.example.todolist.data.local.ToDoListDAO
import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.repository.ToDoListRepository
import kotlinx.coroutines.flow.Flow

class ToDoListRepositoryImpl(private val dao: ToDoListDAO) : ToDoListRepository {
    override suspend fun save(toDo: ToDo) = dao.save(toDo)

    override suspend fun update(toDo: ToDo) = dao.update(toDo)

    override suspend fun delete(toDo: ToDo) = dao.delete(toDo)

    override suspend fun deleteAll() = dao.deleteAll()

    override fun getToDoList(): Flow<List<ToDo>> = dao.getToDoList()

    override fun getToDoListPriority(priority: Int): Flow<List<ToDo>> = dao.getToDoByPriority(priority)

    override fun searchToDo(query: String): List<ToDo> = dao.searchToDo(query)
}