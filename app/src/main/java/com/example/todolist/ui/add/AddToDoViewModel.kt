package com.example.todolist.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.model.ToDo
import com.example.todolist.domain.usecase.add.AddToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(

    private val addToDoUseCase: AddToDoUseCase

) : ViewModel() {

    fun save(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO) {
        addToDoUseCase.invoke(toDo)
    }


}