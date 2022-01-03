package com.dazz.mytodo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dazz.mytodo.TaskApplication
import com.dazz.mytodo.databinding.FragmentAddTaskBinding
import com.dazz.mytodo.databinding.FragmentHomeBinding
import com.dazz.mytodo.models.TodoViewModel
import com.dazz.mytodo.models.TodoViewModelFactory
import java.util.*

class DashboardFragment : Fragment() {

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}