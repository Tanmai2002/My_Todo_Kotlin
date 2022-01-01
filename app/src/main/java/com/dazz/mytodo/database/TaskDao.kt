package com.dazz.mytodo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("Select * From tasks")
    fun getAllTasks() :List<Task>

    @Insert
    suspend fun insertTask(task :Task)

    @Query("Select * From tasks Where id=:id")
    fun getTask(id : Long) : Task

    @Delete
    suspend fun deleteTask(task :Task)
}