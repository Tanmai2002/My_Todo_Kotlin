package com.dazz.mytodo

import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dazz.mytodo.adapters.AllTasksViewAdapter
import com.dazz.mytodo.adapters.DashBoardAdapter
import com.dazz.mytodo.database.Task
import com.dazz.mytodo.models.TodoViewModel
import java.sql.Date

@BindingAdapter("setDate")
fun setDate(textView: TextView,date : LiveData<Date>?){
    textView.setText(date?.value!!.toString())
}

@BindingAdapter("endDateVisibility")
fun eDAteVisibility(textView: View,boolean: Boolean){
    if(boolean){
        textView.visibility=View.VISIBLE
    }else {
        textView.visibility = View.INVISIBLE
    }
}

@BindingAdapter("taskList1","taskList2","taskList3","choice")
fun bindTaskList(recyclerView: RecyclerView,list1: List<Task>?,list2: List<Task>?,list3: List<Task>?,i :Int){
    val adapter= recyclerView.adapter as DashBoardAdapter
    when(i) {
        1->adapter.submitList(list1)
        2->adapter.submitList(list2)
        3->adapter.submitList(list3)
    }
}

