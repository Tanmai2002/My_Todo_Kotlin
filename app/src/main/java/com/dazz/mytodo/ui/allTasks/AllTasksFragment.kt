package com.dazz.mytodo.ui.allTasks

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazz.mytodo.TaskApplication
import com.dazz.mytodo.adapters.AllTasksViewAdapter
import com.dazz.mytodo.databinding.FragmentGalleryBinding
import com.dazz.mytodo.models.TodoViewModel
import com.dazz.mytodo.models.TodoViewModelFactory
import java.sql.Date

class AllTasksFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
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

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.apply {
            viewModel=sharedViewModel
            fragVar=this@AllTasksFragment
            lifecycleOwner=viewLifecycleOwner


        }
        val adapter= AllTasksViewAdapter(sharedViewModel){

        }
        _binding!!.listTaskRV.adapter=adapter

        _binding!!.listTaskRV.layoutManager=LinearLayoutManager(this.context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}