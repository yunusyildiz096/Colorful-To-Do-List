package com.example.todolist.domain.usecase.settings

import com.example.todolist.domain.repository.ToDoListRepository
import javax.inject.Inject

class DeleteAllUseCase @Inject constructor(private val repo : ToDoListRepository) {
    suspend operator fun invoke() = repo.deleteAll()
}