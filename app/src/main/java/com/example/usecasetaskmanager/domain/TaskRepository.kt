package com.example.usecasetaskmanager.domain

import com.example.usecasetaskmanager.data.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun getTaskById(id: Int): Task?
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}