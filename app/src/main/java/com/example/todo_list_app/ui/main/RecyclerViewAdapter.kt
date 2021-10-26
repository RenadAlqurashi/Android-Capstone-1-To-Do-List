package com.example.todo_list_app.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list_app.R
import com.example.todo_list_app.data.Task


class RecyclerViewAdapter(private val taskList: List<Task>): RecyclerView.Adapter<TaskAdapter>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return TaskAdapter(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter, position: Int) {
        val task = taskList[position]
         holder.titleTextView.text = task.taskName
         holder.dateTextView.text = task.taskDate.toString()
         holder.timeTextView.text = task.taskTime.toString()
         holder.descriptionTextView.text = task.taskDescription

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}

class TaskAdapter(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
    val titleTextView:TextView = itemView.findViewById(R.id.taskTitel)
    val dateTextView:TextView = itemView.findViewById(R.id.taskDate)
    val timeTextView:TextView = itemView.findViewById(R.id.taskTime)
    val descriptionTextView:TextView=itemView.findViewById(R.id.taskDescription)
    val isDoneCheckBox:CheckBox = itemView.findViewById(R.id.checkbox)
    init {
        itemView.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        Toast.makeText(itemView.context, "${titleTextView.text} clicked", Toast.LENGTH_SHORT).show()
    }

}

