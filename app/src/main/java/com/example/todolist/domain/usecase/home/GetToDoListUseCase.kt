package com.example.todolist.domain.usecase.home

import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class GetToDoListUseCase @Inject constructor(private val repo: ToDoListRepository) {
    operator fun invoke(): List<ToDo> = repo.getToDoList()
}