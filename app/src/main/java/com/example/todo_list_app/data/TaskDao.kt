package com.example.todo_list_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("select * From task_table ")
    suspend fun getAllUsers() :MutableList<Task>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)


    @Query("SELECT * FROM task_table ORDER BY taskId ASC")
    fun readAllData(): LiveData<List<Task>>



}