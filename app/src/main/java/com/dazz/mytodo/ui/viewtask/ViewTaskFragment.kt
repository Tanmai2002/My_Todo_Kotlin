package com.dazz.mytodo.ui.viewtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.dazz.mytodo.R
import com.dazz.mytodo.TaskApplication
import com.dazz.mytodo.database.Task
import com.dazz.mytodo.databinding.FragmentHomeBinding
import com.dazz.mytodo.databinding.FragmentViewTaskBinding
import com.dazz.mytodo.models.TodoViewModel
import com.dazz.mytodo.models.TodoViewModelFactory
import java.sql.Date


class ViewTaskFragment : Fragment() {
    private val navAr : ViewTaskFragmentArgs by  navArgs()
    private val sharedViewModel: TodoViewModel by activityViewModels{
        TodoViewModelFactory(
            (activity?.application as TaskApplication).database.taskDao()
        )
    }
//   val myTask : LiveData<Task> =sharedViewModel.retrieveTask(navAr.taskid)

    var myTask : LiveData<Task> ?=null
        private var _binding: FragmentViewTaskBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentViewTaskBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val t=navAr.taskid
        myTask=sharedViewModel.retrieveTask(t)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.apply {
            viewModel=sharedViewModel
            viewFrag=this@ViewTaskFragment
            lifecycleOwner=viewLifecycleOwner
        }

    }


}