package com.example.todo_list_app.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class taskViewModel(application: Application) : AndroidViewModel(application){
    private val readAllData: LiveData<List<Task>>
    private val repo:Repo

    init {
        val taskDao =AppDataBase.getAppDataBase(application).taskDao()
        repo = Repo(taskDao)
        readAllData=repo.readAllData
    }

    fun insert(task:Task){
        viewModelScope.launch (Dispatchers.IO){
            repo.insert(task)
        }
    }

}

