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
import android.widget.DatePicker
import android.widget.TimePicker








class NewTaskFragment : Fragment() {
    private lateinit var viewModel:NewTaskViewModel
    private lateinit var taskTitle: EditText
    private lateinit var taskDescription: EditText
    private lateinit var taskDateButton: FloatingActionButton
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
        taskInsertButton = view.findViewById(R.id.taskInsertButton)
        showDate = view.findViewById(R.id.showDate)


        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)
        var getDate=" "

        taskDateButton.setOnClickListener {
            val pickDate=DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(
                        requireContext(),
                        TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                            val pickedDateTime = Calendar.getInstance()
                            pickedDateTime.set(year, month, day, hour, minute)
                            getDate = "$day-$month-$year $hour:$minute"
                            showDate.setText(getDate)
                        },
                        startHour,
                        startMinute,
                        false
                    ).show()
                },
                startYear,
                startMonth,
                startDay
            )
            pickDate.datePicker.minDate= currentDateTime.timeInMillis
            pickDate.show()
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
        val taskDescription = taskDescription.text.toString()
        val taskDate = showDate.text.toString()

        val newTask =Task(
            taskName= taskTitle,
            creationDate= currentDate,
            taskDate= taskDate,
            taskDescription= taskDescription
        )

            viewModel.insert(newTask)

    }
}

