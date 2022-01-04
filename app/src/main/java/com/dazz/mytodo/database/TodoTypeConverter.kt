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
//    @TypeConverter
//    fun boolToInt(b : Boolean ) :Int {
//        if(b)
//            return 1
//        else
//            return 0
//    }
//    @TypeConverter
//    fun IntToBool(i :Int) :Boolean{
//        return i==1
//    }
}