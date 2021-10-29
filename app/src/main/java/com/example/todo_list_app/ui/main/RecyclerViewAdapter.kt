package com.example.todo_list_app.ui.main

import android.icu.text.SimpleDateFormat
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
import com.example.todo_list_app.databinding.TestBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class RecyclerViewAdapter(
    private var taskList: List<Task>,
    private val viewModel: MainViewModel
    ): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TestBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(taskList[position]) {
                binding.taskTitle.text = this.taskName
                binding.taskDate.text = this.taskDate.toString()
                binding.taskDescription.text = this.taskDescription

                val sdf = SimpleDateFormat("dd/M/yyyy  hh:mm")
                val currentDate = sdf.format(Date())

                if (this.isTaskDone) {
                    binding.isDoneCheckbox.isChecked = true
                    if (this.taskDate!! > currentDate) {
                        binding.isDoneCheckbox.setEnabled(false)
                    }
                }
                binding.isDoneCheckbox.setOnCheckedChangeListener { _, isTaskDone ->
                    this.isTaskDone = isTaskDone
                    viewModel.update(this)
                }
                viewModel.update(this)
                this.isTaskDone = binding.isDoneCheckbox.isChecked

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
                binding.expandedView.visibility = if (this.expand) View.VISIBLE else View.GONE

                binding.cardLayout.setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
                binding.taskDeleteButton.setOnClickListener {
                    viewModel.delete(this)
                    taskList -= this
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, getItemCount())
                }
                binding.taskEditButton.setOnClickListener {
                    val action: NavDirections =
                        MainFragmentDirections.actionMainFragmentToEditTaskFragment(this)
                    it.findNavController().navigate(action)
                    viewModel.update(this)
                }

//                holder.expandedView.visibility
                //                }
            }

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
    override fun getItemCount(): Int {
        return taskList.size
    }
}


class TaskAdapter(itemView: View):RecyclerView.ViewHolder(itemView) {
//    val titleTextView:TextView = itemView.findViewById(R.id.taskTitle)
//    val dateTextView:TextView = itemView.findViewById(R.id.taskDate)
//    val descriptionTextView:TextView=itemView.findViewById(R.id.taskDescription)
//    val isDoneCheckBox:CheckBox = itemView.findViewById(R.id.isDoneCheckbox)
//    val deleteButton: FloatingActionButton = itemView.findViewById(R.id.taskDeleteButton)
    val updateButton: FloatingActionButton = itemView.findViewById(R.id.taskEditButton)

}

