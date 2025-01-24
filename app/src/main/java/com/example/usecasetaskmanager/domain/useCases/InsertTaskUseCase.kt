package com.example.usecasetaskmanager.domain.useCases

import com.example.usecasetaskmanager.data.Task
import com.example.usecasetaskmanager.domain.TaskRepository

class InsertTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task){
        repository.insertTask(task)
    }
}