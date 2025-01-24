package com.example.usecasetaskmanager.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecasetaskmanager.data.Task
import com.example.usecasetaskmanager.data.TaskRepositoryImpl
import com.example.usecasetaskmanager.domain.TaskRepository
import com.example.usecasetaskmanager.domain.useCases.DeleteTaskUseCase
import com.example.usecasetaskmanager.domain.useCases.GetTasksUseCase
import com.example.usecasetaskmanager.domain.useCases.InsertTaskUseCase
import com.example.usecasetaskmanager.domain.useCases.UpdateTaskUseCase
import kotlinx.coroutines.launch

class TaskViewModel(
) : ViewModel() {
    private val repository: TaskRepository = TaskRepositoryImpl()
    val getTasksUseCase = GetTasksUseCase(repository)
    val deleteTaskUseCase = DeleteTaskUseCase(repository)
    val insertTaskUseCase = InsertTaskUseCase(repository)
    val updateTaskUseCase = UpdateTaskUseCase(repository)

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun loadTasks(){
        viewModelScope.launch {
            _tasks.value = getTasksUseCase()
        }
    }

    fun addTask(title: String, description: String)
    {
        viewModelScope.launch {
            insertTaskUseCase(Task(title = title, description = description))
            loadTasks()
        }
    }

    fun updateTasks(task: Task)
    {
        viewModelScope.launch {
            updateTaskUseCase(task)
            loadTasks()
        }
    }

    fun deleteTask(task: Task)
    {
        viewModelScope.launch {
            deleteTaskUseCase(task)
            loadTasks()
        }
    }

}