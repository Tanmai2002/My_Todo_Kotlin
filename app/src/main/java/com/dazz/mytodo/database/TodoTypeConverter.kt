package com.dazz.mytodo.database

import androidx.room.TypeConverter
import java.sql.Date

class TodoTypeConverter {
@TypeConverter
fun DateToLong(date : Date) : String{
    return date.toString()
}
    @TypeConverter
    fun LongToDate(long: String) :Date{
        return Date.valueOf(long)
    }
}