package com.example.todolist.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.usecase.settings.DeleteAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModel() {

    fun deleteAll() = viewModelScope.launch {
        deleteAllUseCase.invoke()
    }
}