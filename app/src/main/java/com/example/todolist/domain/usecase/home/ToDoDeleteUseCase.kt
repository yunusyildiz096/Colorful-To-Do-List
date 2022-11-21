package com.example.todolist.domain.usecase.home

import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class ToDoDeleteUseCase @Inject constructor(private val repo: ToDoListRepository) {
    suspend operator fun invoke(toDo: ToDo) = repo.delete(toDo)
}