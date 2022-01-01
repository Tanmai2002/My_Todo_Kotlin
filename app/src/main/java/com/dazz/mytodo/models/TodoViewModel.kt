package com.dazz.mytodo.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dazz.mytodo.database.TaskDao

class TodoViewModel(private  val itemDao: TaskDao) : ViewModel() {

}

class TodoViewModelFactory(private val taskDao : TaskDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}