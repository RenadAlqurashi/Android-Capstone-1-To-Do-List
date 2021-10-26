package com.example.todo_list_app.data

import androidx.lifecycle.LiveData

class Repo(private val tasKDao:TaskDao) {

    val readAllData: LiveData<List<Task>> = tasKDao.readAllData()

    suspend fun insert(task:Task){
        tasKDao.insert(task)
    }

//    suspend fun delete(task: Task){
//        tasKDao.delete(task)
//    }
//
//    suspend fun update(task: Task){
//        tasKDao.update(task)
//    }

}