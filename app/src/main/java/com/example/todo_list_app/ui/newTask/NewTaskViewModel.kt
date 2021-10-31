package com.example.todo_list_app.ui.newTask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list_app.data.Repo
import com.example.todo_list_app.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = Repo(context)

    fun insert(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            repo.insert(task)
        }
    }

}