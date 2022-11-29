package com.example.todolist.data.local

import androidx.room.*
import com.example.todolist.data.model.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDAO {
    @Query("SELECT * FROM toDo")
    fun getToDoList(): Flow<List<ToDo>>

    @Query("SELECT * FROM toDo WHERE priority=:priority")
    fun getToDoByPriority(priority: Int): Flow<List<ToDo>>

    @Query("SELECT * FROM toDo WHERE title LIKE:query OR description LIKE:query")
    fun searchToDo(query: String): List<ToDo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(toDo: ToDo)

    @Update
    suspend fun update(toDo: ToDo)

    @Query("DELETE FROM toDo")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(toDo: ToDo)
}