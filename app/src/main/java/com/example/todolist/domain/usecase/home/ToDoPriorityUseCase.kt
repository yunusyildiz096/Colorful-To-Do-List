package com.example.todolist.domain.usecase.home

import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.repository.ToDoListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoPriorityUseCase @Inject constructor(private val repo: ToDoListRepository) {
    operator fun invoke(priority: Int): Flow<List<ToDo>> = repo.getToDoListPriority(priority)
}