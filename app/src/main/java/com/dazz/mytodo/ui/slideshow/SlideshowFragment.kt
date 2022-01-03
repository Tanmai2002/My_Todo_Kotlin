package com.dazz.mytodo.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dazz.mytodo.TaskApplication
import com.dazz.mytodo.databinding.FragmentSlideshowBinding
import com.dazz.mytodo.models.TodoViewModel
import com.dazz.mytodo.models.TodoViewModelFactory

class SlideshowFragment : Fragment() {
    private val sharedViewModel: TodoViewModel by activityViewModels{
        TodoViewModelFactory(
            (activity?.application as TaskApplication).database.taskDao()
        )
    }
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}