package com.example.usecasetaskmanager.domain.useCases

import com.example.usecasetaskmanager.data.Task
import com.example.usecasetaskmanager.domain.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task){
        repository.deleteTask(task)
    }
}