package com.dazz.mytodo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "dailyTaskStatus")
data class TaskStatus(
    @PrimaryKey(autoGenerate = true) val prid :Long=0,
    @ColumnInfo(name="taskId") val id :Long =0,
    @ColumnInfo(name = "Date") val date :Date,
    @ColumnInfo(name="Status") val status :Boolean
)