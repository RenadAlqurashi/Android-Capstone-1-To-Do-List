package com.example.todo_list_app.ui.editTask

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo_list_app.R
import com.example.todo_list_app.data.Task
import com.example.todo_list_app.ui.newTask.NewTaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class EditTaskFragment : Fragment() {
    private lateinit var viewModel: EditTaskViewModel
    private lateinit var taskTitle: EditText
    private lateinit var taskDescription: EditText
    private lateinit var taskDateButton: FloatingActionButton
    private lateinit var taskTimeButton: FloatingActionButton
    private lateinit var taskEditButton: FloatingActionButton
    private lateinit var showDate: TextView
    private lateinit var showTime: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        taskTitle = view.findViewById(R.id.taskTitle)
        taskDescription = view.findViewById(R.id.taskDescription)
        taskDateButton = view.findViewById(R.id.setDateButton)
        taskTimeButton = view.findViewById(R.id.setTimeButton)
        taskEditButton = view.findViewById(R.id.taskEditButton_editTaskFragment)
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

        taskDateButton.setOnClickListener {
            val pickDate = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, y, m, d ->
                    getDate = "$d-${m + 1}-$y"
                    showDate.setText(getDate)
                },
                year,
                month,
                day
            )
            pickDate.datePicker.minDate = cal.timeInMillis
            pickDate.show()
        }

        taskTimeButton.setOnClickListener {
            val pickTime = TimePickerDialog(
                requireContext(),
                TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    getTime = "$hour:$minute"
                    showTime.setText(getTime.toString())
                },
                hour,
                minute,
                false
            ).show()
        }


        val args: EditTaskFragmentArgs by navArgs()
        val editTaskKey= args.editTaskKey
        taskTitle.setText(args.editTaskKey.taskName)
        taskDescription.setText(args.editTaskKey.taskDescription)
        showDate.setText(args.editTaskKey.taskDate)
        showTime.setText(args.editTaskKey.taskTime)


        taskEditButton.setOnClickListener {
//            updateDataToDataBase()
            viewModel = ViewModelProvider(this).get(EditTaskViewModel::class.java)

            val sdf = SimpleDateFormat("dd/M/yyyy  hh:mm")
            val currentDate = sdf.format(Date())

//            val taskTitle = taskTitle.text.toString()
//            val taskDescription = taskDescription.text.toString()
//            val taskDate = showDate.text.toString()
//            val taskTime = showTime.text.toString()

//            val updatedTask = Task(
//                taskName= taskTitle,
//                creationDate= currentDate,
//                taskDate= taskDate,
//                taskTime= taskTime,
//                taskDescription= taskDescription
//            )
            args.editTaskKey.taskName = taskTitle.text.toString()
            args.editTaskKey.taskDescription = taskDescription.text.toString()
            args.editTaskKey.taskDate = showDate.text.toString()
            args.editTaskKey.taskTime = showTime.text.toString()
            args.editTaskKey.creationDate = currentDate.toString()



            viewModel.update(args.editTaskKey)

            findNavController().navigate(R.id.action_editTaskFragment_to_mainFragment)
        }

    }

//    private fun updateDataToDataBase() {
//    }

}