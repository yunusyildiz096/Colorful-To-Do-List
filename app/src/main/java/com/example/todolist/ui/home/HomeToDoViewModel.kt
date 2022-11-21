package com.example.todolist.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.usecase.add.AddToDoUseCase
import com.example.todolist.domain.usecase.home.GetToDoListUseCase
import com.example.todolist.domain.usecase.home.SearchToDoUseCase
import com.example.todolist.domain.usecase.home.ToDoDeleteUseCase
import com.example.todolist.domain.usecase.home.ToDoPriorityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeToDoViewModel @Inject constructor(

    private val getToDoListUseCase: GetToDoListUseCase,
    private val toDoDeleteUseCase: ToDoDeleteUseCase,
    private val toDoPriorityUseCase: ToDoPriorityUseCase,
    private val searchToDoUseCase: SearchToDoUseCase,
    private val addToDoUseCase: AddToDoUseCase

) : ViewModel() {

    private var _toDoList = MutableLiveData<List<ToDo>>()
    val toDoList: LiveData<List<ToDo>> = _toDoList

    init {
        getToDoList()
    }

    fun getToDoList() {
        _toDoList.value = getToDoListUseCase.invoke()
    }

    fun getTodoByPriority(priority: Int) {
        _toDoList.value = toDoPriorityUseCase.invoke(priority)
    }

    fun searchToDo(query: String): LiveData<List<ToDo>> = searchToDoUseCase.invoke(query)

    fun delete(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO) {
        toDoDeleteUseCase.invoke(toDo)
    }

    fun save(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO) {
        addToDoUseCase.invoke(toDo)
    }


}