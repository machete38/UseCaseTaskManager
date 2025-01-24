package com.example.usecasetaskmanager.data

import com.example.usecasetaskmanager.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepositoryImpl: TaskRepository {

    private val tasks = mutableListOf<Task>()
    private var lastId = 0

    override suspend fun getTasks(): List<Task> = withContext(Dispatchers.IO)
    {
        tasks.toList()
    }

    override suspend fun getTaskById(id: Int): Task? = withContext(Dispatchers.IO)
    {
        tasks.find { it.id == id }
    }

    override suspend fun insertTask(task: Task): Unit = withContext(Dispatchers.IO)
    {
        val newTask = task.copy(id = ++lastId)
        tasks.add(newTask)
    }

    override suspend fun updateTask(task: Task) = withContext(Dispatchers.IO)
    {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) tasks[index] = task
    }

    override suspend fun deleteTask(task: Task): Unit = withContext(Dispatchers.IO)
    {
        tasks.removeAll { it.id == task.id }
    }
}