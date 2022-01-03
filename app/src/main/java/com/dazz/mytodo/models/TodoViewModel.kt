package com.dazz.mytodo.models

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.dazz.mytodo.database.Task
import com.dazz.mytodo.database.TaskDao
import kotlinx.coroutines.launch
import java.sql.Date

class TodoViewModel(private  val taskDao: TaskDao) : ViewModel() {
    private var allItems=taskDao.getAllTasks().asLiveData()
    fun retrieveTask(id :Long) : LiveData<Task>{
        return taskDao.getTask(id).asLiveData()
    }
    fun insertTask(task : Task){
        viewModelScope.launch{
            taskDao.insertTask(task)
        }
    }
    fun updateTask(task : Task){
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }
    private fun makeNewTask(title :String,Desc :String ,Sdate :Date,Edate :Date,Type :String) :Task{
        return Task(start_date = Sdate,end_date = Edate,title = title,desc = Desc,type = Type)
    }
    fun addNewTask(title :String,Desc :String ,Sdate :Date,Edate :Date,Type :String){
            val t=makeNewTask(title,Desc,Sdate,Edate,Type)
        insertTask(t)
    }


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