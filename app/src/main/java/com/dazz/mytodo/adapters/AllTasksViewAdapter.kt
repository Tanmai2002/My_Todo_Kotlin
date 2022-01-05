package com.dazz.mytodo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dazz.mytodo.R
import com.dazz.mytodo.database.Task
import com.dazz.mytodo.databinding.FragmentAddTaskBinding
import com.dazz.mytodo.databinding.TaskListTaskBinding
import com.dazz.mytodo.models.TodoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AllTasksViewAdapter(private val viewModel: TodoViewModel,private  val onItemClicked :(Task)->Unit) : ListAdapter<Task,AllTasksViewAdapter.MyViewHolder>(DiffCallback) {
    class MyViewHolder(val binding : TaskListTaskBinding) : RecyclerView.ViewHolder(binding.root){

    }

    lateinit var con :Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        con=parent.context
        return MyViewHolder(
            TaskListTaskBinding.inflate(
            LayoutInflater.from(parent.context)
        ,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val v:Task=getItem(position)
        holder.binding.myTask=v
        holder.binding.imageButton.setOnClickListener{
            MaterialAlertDialogBuilder(con)
                .setTitle("Are You Sure to Delete")
                .setMessage("Your Task will be deleted Forever")
                .setCancelable(false)
                .setNegativeButton("NO") { _, _ -> }
                .setPositiveButton("YES") { _, _ ->
                    viewModel.deleteTask(v)
                }
                .show()
        }
    }

    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}