package com.dazz.mytodo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazz.mytodo.TaskApplication
import com.dazz.mytodo.adapters.DashBoardAdapter
import com.dazz.mytodo.databinding.FragmentAddTaskBinding
import com.dazz.mytodo.databinding.FragmentHomeBinding
import com.dazz.mytodo.models.TodoViewModel
import com.dazz.mytodo.models.TodoViewModelFactory
import java.util.*

class DashboardFragment : Fragment() {
    val choice : MutableLiveData<Int> =MutableLiveData(1)
    private var _binding: FragmentHomeBinding? = null
    private val sharedViewModel: TodoViewModel by activityViewModels{
        TodoViewModelFactory(
            (activity?.application as TaskApplication).database.taskDao()
        )
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.apply {
            viewModel=sharedViewModel
            lifecycleOwner=viewLifecycleOwner
            dashFrag=this@DashboardFragment
            dashboardTaskList.adapter=DashBoardAdapter(sharedViewModel){
                val action=DashboardFragmentDirections.actionNavHomeToViewTaskFragment(it.id)
                findNavController().navigate(action)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
fun setChoice(i :Int){
    choice.value=i
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}