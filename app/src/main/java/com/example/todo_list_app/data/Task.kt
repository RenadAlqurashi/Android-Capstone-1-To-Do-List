package com.example.todo_list_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val taskName: String,
    val creationDate: String,
    val taskDate: String,
    val creationTime:String,
    val taskTime: String,
    val taskDescription: String,
    val isTaskDone: Boolean
        )