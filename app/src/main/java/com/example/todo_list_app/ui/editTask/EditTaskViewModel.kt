package com.example.todo_list_app.ui.editTask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list_app.data.Repo
import com.example.todo_list_app.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTaskViewModel (context: Application) : AndroidViewModel(context) {
    private val repo = Repo(context)

    fun update(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            repo.update(task)
        }
    }
}