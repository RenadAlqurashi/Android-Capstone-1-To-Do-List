package com.example.todo_list_app.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list_app.data.Repo
import com.example.todo_list_app.data.Task
import kotlinx.coroutines.launch

class MainViewModel(context: Application) : AndroidViewModel(context){
    private val repo = Repo(context)

    fun getAllTasks(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAllTasks())
        }
        return tasks
    }
}