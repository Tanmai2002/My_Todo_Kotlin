package com.dazz.mytodo.database

import androidx.room.TypeConverter
import java.sql.Date

class TodoTypeConverter {
@TypeConverter
fun DateToLong(date : Date) : Long{
    return DateToLong(date)
}
    @TypeConverter
    fun LongToDate(long: Long) :Date{
        return Date(long)
    }
}