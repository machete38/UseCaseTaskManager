package com.example.usecasetaskmanager.domain.useCases

import com.example.usecasetaskmanager.data.Task
import com.example.usecasetaskmanager.domain.TaskRepository

class GetTasksUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(): List<Task>{
        return repository.getTasks()
    }
}