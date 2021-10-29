package com.example.todo_list_app.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "task_table")
@Parcelize
data class Task (
    @PrimaryKey(autoGenerate = true)
    var taskId: Int =0,
    var taskName: String,
    var creationDate: String,
    var taskDate: String?,
    var taskDescription: String?,
    var isTaskDone: Boolean = false
        ): Parcelable