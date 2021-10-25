package com.example.todo_list_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
public abstract class AppDataBase: RoomDatabase() {
    abstract val userDao: TaskDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getAppDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app-database"
                ).build()
            }
            return INSTANCE
        }
    }
}