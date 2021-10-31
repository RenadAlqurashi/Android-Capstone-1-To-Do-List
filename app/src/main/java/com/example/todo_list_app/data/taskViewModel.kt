package com.example.todo_list_app.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class taskViewModel(context: Application) : AndroidViewModel(context){
    private val repo = Repo(context)

    fun getAllTasks(): MutableLiveData<List<Task>>{
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAllTasks())
        }
        return tasks
    }
    fun insert(task:Task){
        viewModelScope.launch (Dispatchers.IO){
            repo.insert(task)
        }
    }
    fun update(task:Task){
        viewModelScope.launch (Dispatchers.IO){
            repo.update(task)
        }
    }
    fun delete(task:Task){
        viewModelScope.launch (Dispatchers.IO){
            repo.delete(task)
        }
    }
}

