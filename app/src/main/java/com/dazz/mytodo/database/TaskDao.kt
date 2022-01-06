package com.dazz.mytodo.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface TaskDao {
    @Query("Select * From tasks order by `Start Date` asc")
    fun getAllTasks() :Flow<List<Task>>

    @Query("Select * From tasks where type='Daily' and id not in (select distinct taskId from dailyTaskStatus)")
    fun getAllTasksWithDailyNotSet() :Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task :Task) :Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatusDaily(taskStatus: TaskStatus)

    @Update
    suspend fun updateTask(task :Task)

    @Query("Select * From tasks Where id=:id")
    fun getTask(id : Long) : Flow<Task>

    @Delete
    suspend fun deleteTask(task :Task)

    @Query("Select * From tasks Where (`Start Date`=:date and Type='Once') || (`Start Date`<=:date and `End Date`>=:date and Type='Daily') order by `Start Date` ")
    fun getAllTaskToday(date: Date) :Flow<List<Task>>
    @Query("Select * From tasks Where (`Start Date`<=:date and `End Date`>=:date and Type='Month') order by `Start Date`")
    fun getAllTaskMonth(date: Date) :Flow<List<Task>>
    @Query("Select * From tasks Where (`Start Date`<=:date and `End Date`>=:date and Type='Year') order by `Start Date`")
    fun getAllTaskYear(date: Date) :Flow<List<Task>>

    @Query("Update tasks Set Done=:b where id=:id")
    suspend fun chengeDone(id: Long, b: Boolean)

    @Query("Update dailyTaskStatus Set Status=:b where taskId=:id and Date=:d")
    suspend fun chengeDailyDone(id: Long, b: Boolean,d:Date)

}