package com.example.todo_list_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey
    var taskId: Int,
    var taskName: String,
    var taskDate: Int,
    var taskTime: Int,
    var taskDescription: String,
    var isTaskDone: Boolean
        )