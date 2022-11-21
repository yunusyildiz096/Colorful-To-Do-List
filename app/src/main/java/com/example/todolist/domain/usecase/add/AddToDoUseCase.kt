package com.example.todolist.domain.usecase.add

import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class AddToDoUseCase @Inject constructor(private val repo: ToDoListRepository) {
    suspend operator fun invoke(toDo: ToDo) = repo.save(toDo)
}