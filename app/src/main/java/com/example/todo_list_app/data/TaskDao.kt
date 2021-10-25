package com.example.todo_list_app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("select * From task_table ")
    suspend fun getAllTasks() : List<Task>

    @Update()
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

//    @Query("select * from task_table where taskDate== :tDate")
//    suspend fun selectTaskByDate(tDate: Int): Task
//
//    @Query("select * from task_table where taskName== :tName")
//    suspend fun selectTaskByName(tName: String): Task
//
//    @Query("select * from task_table where isTaskDone== :tDone")
//    suspend fun selectTaskByDate(tDone: Boolean): Task

    @Query("SELECT * FROM task_table ORDER BY taskName ASC")
    fun getAlphabetizedTasks(): Flow<List<Task>>



}