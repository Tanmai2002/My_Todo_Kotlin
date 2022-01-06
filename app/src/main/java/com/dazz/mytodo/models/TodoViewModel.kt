package com.dazz.mytodo.models

import android.util.Log
import androidx.core.util.toRange
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.dazz.mytodo.database.Task
import com.dazz.mytodo.database.TaskDao
import com.dazz.mytodo.database.TaskStatus
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.LocalDate
import java.util.*

class TodoViewModel(private  val taskDao: TaskDao) : ViewModel() {
    private val today=Date.valueOf(LocalDate.now().toString())
     val allItems : LiveData<List<Task>> =taskDao.getAllTasks().asLiveData()
    val allItemsToday : LiveData<List<Task>> get()=taskDao.getAllTaskToday(today).asLiveData()
    val allItemsMonth : LiveData<List<Task>> =taskDao.getAllTaskMonth(today).asLiveData()
    val allItemsYear : LiveData<List<Task>> =taskDao.getAllTaskYear(today).asLiveData()
    private val l=taskDao.getAllTasksWithDailyNotSet().asLiveData()


    fun retrieveTask(id :Long) : LiveData<Task>{
        return taskDao.getTask(id).asLiveData()
    }
    fun insertTask(task : Task){
        viewModelScope.launch{
            val v=taskDao.insertTask(task)
            val tTAsk=task.copy(id=v)
            makeStatusids(tTAsk)
                }
        }
    fun makeStatusids(i : Task){

            val cal=Calendar.getInstance()
            cal.time=Date.valueOf(i.start_date.toString())
            while(cal.time.before(i.end_date)) {
                val mydat =cal.time as java.util.Date
                val mydat2=Date(mydat.time)
                val ts = TaskStatus(0,i.id,mydat2,false)
                viewModelScope.launch{
                    taskDao.insertStatusDaily(ts)
                }
                cal.add(Calendar.DATE,1)

        }

    }


    fun deleteTask(task : Task){
        viewModelScope.launch{
            taskDao.deleteTask(task)
        }
    }
    fun updateTask(task : Task){
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }
    fun changeStatus(id : Task,b : Boolean){
        viewModelScope.launch {

                taskDao.chengeDone(id.id, b)
            if(id.type.equals("Daily")) {
                val date :Date= Date.valueOf(LocalDate.now().toString())
                taskDao.chengeDailyDone(id.id, b,date)
            }
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