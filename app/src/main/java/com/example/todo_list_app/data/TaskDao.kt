package com.example.todo_list_app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

//    @Update()
//    suspend fun update(task: Task)
//
//    @Delete
//    suspend fun delete(task: Task)


    @Query("SELECT * FROM task_table ORDER BY taskId ASC")
    fun readAllData(): LiveData<List<Task>>



}