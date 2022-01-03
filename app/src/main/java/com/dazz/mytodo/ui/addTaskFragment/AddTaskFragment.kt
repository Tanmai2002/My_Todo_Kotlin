package com.dazz.mytodo.ui.addTaskFragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.dazz.mytodo.R
import com.dazz.mytodo.TaskApplication
import com.dazz.mytodo.databinding.FragmentAddTaskBinding
import com.dazz.mytodo.models.TodoViewModel
import com.dazz.mytodo.models.TodoViewModelFactory
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


class AddTaskFragment : Fragment() {

    private  var _binding : FragmentAddTaskBinding?= null
    private val binding get() = _binding!!

    var _SDAte =MutableLiveData<Date>(Date.valueOf("2002-02-02"))
    var SDate : LiveData<Date>  =_SDAte
    var _EDAte =MutableLiveData<Date>(Date.valueOf("2002-02-02"))
    val EDate : LiveData<Date>  =_EDAte
    var _Type =MutableLiveData<String>("")
    var Type :LiveData<String> =_Type
    val l= listOf<String>("Once","Daily","Month","Year")
    private val sharedViewModel: TodoViewModel by activityViewModels{
        TodoViewModelFactory(
            (activity?.application as TaskApplication).database.taskDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAddTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.apply {
            viewModel =sharedViewModel
            addTaskFrag=this@AddTaskFragment
            lifecycleOwner=viewLifecycleOwner

        }
        resetAll()
    }
    fun submitTask(){
        if(_binding!!.addTaskTitle.text.toString().isBlank() ){
            Toast.makeText(requireContext(),"Please Enter all Details",Toast.LENGTH_LONG).show()
            return
        }
        sharedViewModel.addNewTask(_binding!!.addTaskTitle.text.toString(),_binding!!.addTaskDesc.text.toString()
            ,SDate.value!!,EDate.value!!,Type.value!!)
//        findNavController().popBackStack()
        resetAll()
        findNavController().navigateUp()
    }
    /**
     *State 0:StartDate
     * State 1: EndDate
     */

    fun setDate(state :Int){
        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val s :String="${year}-${monthOfYear+1}-${dayOfMonth}"
            Toast.makeText(requireContext(),s,Toast.LENGTH_LONG).show()
            val d=Date.valueOf(s)

            if(state==0){
                _SDAte.value=d
            }else{
                _EDAte.value=d
            }
        }

            DatePickerDialog(
                requireContext(), datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()


        }
    private  fun resetAll(){
        _binding!!.addTaskTitle.setText("")
        _binding!!.addTaskDesc.setText("")
        _EDAte.value= Date.valueOf("2002-02-02")
        _SDAte.value= Date.valueOf("2002-02-02")

        val adaptera =ArrayAdapter<String>(requireContext(),R.layout.support_simple_spinner_dropdown_item,l)
        _binding!!.addTaskType.adapter=adaptera
        _binding!!.addTaskType.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                _Type.value=l.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                _Type.value=l.get(0)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}