package com.dazz.mytodo.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("Select * From tasks order by id asc")
    fun getAllTasks() :Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task :Task)

    @Update
    suspend fun updateTask(task :Task)

    @Query("Select * From tasks Where id=:id")
    fun getTask(id : Long) : Flow<Task>

    @Delete
    suspend fun deleteTask(task :Task)

}