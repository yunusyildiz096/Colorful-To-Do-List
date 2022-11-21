package com.example.todolist.domain.usecase.details

import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class DetailsToDoUseCase @Inject constructor(private val repo: ToDoListRepository) {
    suspend operator fun invoke(toDo: ToDo) = repo.update(toDo)
}