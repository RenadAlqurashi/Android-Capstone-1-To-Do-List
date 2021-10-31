package com.example.todo_list_app.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list_app.data.Task
import com.example.todo_list_app.databinding.RecyclerviewItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class RecyclerViewAdapter(
    private var taskList: List<Task>,
    private val viewModel: MainViewModel ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(taskList[position]) {

                binding.taskTitle.text = this.taskName
                binding.taskDate.text = this.taskDate.toString()
                binding.taskDescription.text = this.taskDescription
                binding.isDoneCheckbox.setOnCheckedChangeListener { _, isTaskDone ->
                    this.isTaskDone = isTaskDone
                    viewModel.update(this)
                }
                viewModel.update(this)
                this.isTaskDone = binding.isDoneCheckbox.isChecked

                if (!this.taskDescription.isNullOrBlank()){
                    binding.notes.visibility = View.VISIBLE
                }

                if (this.isTaskDone) {
                    with(binding) {
                        isDoneCheckbox.isChecked = true
                        outDate.visibility = View.INVISIBLE
                        outDateLine.visibility = View.INVISIBLE
                    }
                } else {
                    //past due
                    if (pastDue(this.taskDate.toString())) {
                        with(binding) {
                            isDoneCheckbox.isEnabled = false
                            outDate.visibility = View.VISIBLE
                            outDateLine.visibility = View.VISIBLE
                            taskDate.setTextColor(Color.parseColor("#CC4D4D"))
                        }
                    }
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

                binding.expandedView.visibility = if (this.expand) View.VISIBLE else View.GONE
                binding.cardLayout.setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }

            }

        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}

fun pastDue(dueDate: String):Boolean {
    if(dueDate.isNullOrBlank()) {
        return false
    }else{
        val formatter = DateTimeFormatter.ofPattern("d-M-yyyy H:m")
        val dateTime = LocalDateTime.parse(dueDate, formatter)
        return dateTime.isBefore(LocalDateTime.now())
    }
}


