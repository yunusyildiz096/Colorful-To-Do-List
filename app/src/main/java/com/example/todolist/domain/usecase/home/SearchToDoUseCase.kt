package com.example.todolist.domain.usecase.home

import androidx.lifecycle.LiveData
import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class SearchToDoUseCase @Inject constructor(private val repo: ToDoListRepository) {
    operator fun invoke(query: String): LiveData<List<ToDo>> = repo.searchToDo(query)
}