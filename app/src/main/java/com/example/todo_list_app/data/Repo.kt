package com.example.todo_list_app.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val appDB = AppDataBase.getAppDataBase(context)!!

    suspend fun getAllTasks(): MutableList<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.getAllUsers()
    }

    suspend fun insert(task:Task){
        appDB.taskDao.insert(task)
    }

    suspend fun delete(task: Task){
        appDB.taskDao.delete(task)
    }

    suspend fun update(task: Task){
        appDB.taskDao.update(task)
    }

}