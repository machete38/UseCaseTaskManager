package com.example.usecasetaskmanager.data

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)
