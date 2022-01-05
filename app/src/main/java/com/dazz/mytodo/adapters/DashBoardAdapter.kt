package com.dazz.mytodo.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dazz.mytodo.R
import com.dazz.mytodo.database.Task
import com.dazz.mytodo.databinding.DashboardListItemBinding
import com.dazz.mytodo.databinding.FragmentAddTaskBinding
import com.dazz.mytodo.databinding.TaskListTaskBinding
import com.dazz.mytodo.models.TodoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DashBoardAdapter(private val viewModel: TodoViewModel, private  val onItemClicked :(Task)->Unit) : ListAdapter<Task,DashBoardAdapter.MyViewHolder>(DiffCallback) {
    class MyViewHolder(val binding : DashboardListItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val root=DashboardListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val v:Task=getItem(position)
        holder.binding.task=v
        holder.binding.viewModel=viewModel
        holder.binding.backLayout.setOnClickListener{
            onItemClicked(v)
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