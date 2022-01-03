package com.dazz.mytodo

import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dazz.mytodo.models.TodoViewModel
import java.sql.Date

@BindingAdapter("setDate")
fun setDate(textView: TextView,date : LiveData<Date>?){
    textView.setText(date?.value!!.toString())
}

@BindingAdapter("type_select")
fun TypeSpinnerAdapter(spinner: Spinner,viewModel :TodoViewModel){

}