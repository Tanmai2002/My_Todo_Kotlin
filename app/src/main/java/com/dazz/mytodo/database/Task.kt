package com.dazz.mytodo.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true) val id : Long=0,
    @NonNull @ColumnInfo(name = "Start Date") val start_date : Date,
    @ColumnInfo(name = "End Date") val end_date : Date,
    @NonNull @ColumnInfo(name = "Type") val type :String,
    @NonNull @ColumnInfo(name ="Title") val title :String,
    @ColumnInfo(name ="Description") val desc :String,
    @ColumnInfo(name="Done") val done :Boolean=false
    )