package com.example.todo_list_app.ui.newTask

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo_list_app.R
import com.example.todo_list_app.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class NewTaskFragment : Fragment() {
    private lateinit var viewModel:NewTaskViewModel
    private lateinit var taskTitle: EditText
    private lateinit var taskDescription: EditText
    private lateinit var taskDateButton: FloatingActionButton
    private lateinit var taskTimeButton: FloatingActionButton
    private lateinit var taskInsertButton: FloatingActionButton
    private lateinit var showDate: TextView
    private lateinit var showTime: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskTitle = view.findViewById(R.id.taskTitle)
        taskDescription = view.findViewById(R.id.taskDescription)
        taskDateButton = view.findViewById(R.id.setDateButton)
        taskTimeButton = view.findViewById(R.id.setTimeButton)
        taskInsertButton = view.findViewById(R.id.taskInsertButton)
        showDate = view.findViewById(R.id.showDate)
        showTime = view.findViewById(R.id.showTime)

        val cal = Calendar.getInstance()
        val minute = cal.get(Calendar.MINUTE)
        val hour = cal.get(Calendar.HOUR)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)

        var getDate = " "
        var getTime = " "

        taskDateButton.setOnClickListener{
            DatePickerDialog(requireContext(),DatePickerDialog.OnDateSetListener{
                    view, y, m, d ->
                    getDate="$d-${m+1}-$y"
                    showDate.setText(getDate)
            },year,month,day).show()
        }

        taskTimeButton.setOnClickListener {
            TimePickerDialog(requireContext(),TimePickerDialog.OnTimeSetListener{
                    timePicker, hour, minute ->
                    getTime = "$hour:$minute"
                    showTime.setText(getTime.toString())
            },hour,minute,true).show()
        }

        taskInsertButton.setOnClickListener{
            insertDataToDataBase()
            findNavController().navigate(R.id.action_newTaskFragment_to_mainFragment)
        }


    }

    private fun insertDataToDataBase() {
        viewModel = ViewModelProvider(this).get(NewTaskViewModel::class.java)

        val sdf = SimpleDateFormat("dd/M/yyyy  hh:mm")
        val currentDate = sdf.format(Date())

        val taskTitle = taskTitle.text.toString()
        val taskDescription = taskDescription.toString()
        val taskDate = showDate.text.toString()
        val taskTime = showTime.text.toString()

        val newTask =Task(0,taskTitle,currentDate,taskDate,taskTime,taskDescription)

            viewModel.insert(newTask)

    }
}

