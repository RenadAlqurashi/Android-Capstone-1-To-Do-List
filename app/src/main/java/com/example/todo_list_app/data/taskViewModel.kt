package com.example.todo_list_app.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class taskViewModel(private val repo: Repo) : ViewModel(){
    val allTasks: LiveData<List<Task>> = repo.allTasks.asLiveData()
    fun insert(task: Task) = viewModelScope.launch {
        repo.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repo.delete(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repo.update(task)
    }

}
class WordViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(taskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return taskViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}