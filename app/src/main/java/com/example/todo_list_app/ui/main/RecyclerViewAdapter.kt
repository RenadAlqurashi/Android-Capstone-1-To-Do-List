package com.example.todo_list_app.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list_app.R
import com.example.todo_list_app.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RecyclerViewAdapter(private var taskList: List<Task>, private val viewModel: MainViewModel): RecyclerView.Adapter<TaskAdapter>(){
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

        if (task.isTaskDone){
            holder.isDoneCheckBox.isChecked=true
        }
         holder.isDoneCheckBox.setOnCheckedChangeListener { _ ,isTaskDone ->
             task.isTaskDone = isTaskDone
             viewModel.update(task)
         }
        viewModel.update(task)
        task.isTaskDone=holder.isDoneCheckBox.isChecked

//             if (holder.isDoneCheckBox.isChecked && task.isTaskDone == true) {
//                 task.isTaskDone = false
//                 viewModel.update(task)
//             } else if (holder.isDoneCheckBox.isChecked) {
//                 task.isTaskDone = true
//                 viewModel.update(task)
//             }
//         }
//             if(task.isTaskDone){
//                 holder.isDoneCheckBox.isChecked=true
//             }
//             if (holder.isDoneCheckBox.isChecked) {
//                 task.isTaskDone = true
//                 viewModel.update(task)
//             }else {
//                 task.isTaskDone = false
//                 viewModel.update(task)
//             }
//             viewModel.update(task)
//            }
//      notifyDataSetChanged()
        holder.deleteButton.setOnClickListener{
            viewModel.delete(task)
            taskList -= task
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, getItemCount())
        }
        holder.updateButton.setOnClickListener{
            val action:NavDirections = MainFragmentDirections.actionMainFragmentToEditTaskFragment(task)
            it.findNavController().navigate(action)
            viewModel.update(task)
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

//    fun itemClicked(checkBox: CheckBox) {
//        if (checkBox.isChecked && task.isTaskDone = true){
//             task.isTaskDone = false
//           ` viewModel.update(task)
//        }else if (checkBox.isChecked){
//             task.isTaskDone = true
//             viewModel.update(task)
//        }

//    }
}

class TaskAdapter(itemView: View):RecyclerView.ViewHolder(itemView) {
    val titleTextView:TextView = itemView.findViewById(R.id.taskTitle)
    val dateTextView:TextView = itemView.findViewById(R.id.taskDate)
    val timeTextView:TextView = itemView.findViewById(R.id.taskTime)
    val descriptionTextView:TextView=itemView.findViewById(R.id.taskDescription)
    val isDoneCheckBox:CheckBox = itemView.findViewById(R.id.isDoneCheckbox)
    val deleteButton: FloatingActionButton = itemView.findViewById(R.id.taskDeleteButton)
    val updateButton: FloatingActionButton = itemView.findViewById(R.id.taskEditButton)

}

