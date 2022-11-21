package com.example.todolist.di

import com.example.todolist.data.local.ToDoListDAO
import com.example.todolist.data.repository.ToDoListRepositoryImpl
import com.example.todolist.domain.repository.ToDoListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provides(dao: ToDoListDAO): ToDoListRepository = ToDoListRepositoryImpl(dao)
}