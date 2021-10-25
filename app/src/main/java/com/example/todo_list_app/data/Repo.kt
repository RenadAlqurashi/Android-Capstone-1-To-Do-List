package com.example.todo_list_app.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repo(private val tasKDao:TaskDao) {

    val allTasks: Flow<List<Task>> = tasKDao.getAlphabetizedTasks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task:Task){
        tasKDao.insert(task)
    }

    suspend fun delete(task: Task){
        tasKDao.delete(task)
    }

    suspend fun update(task: Task){
        tasKDao.update(task)
    }

}