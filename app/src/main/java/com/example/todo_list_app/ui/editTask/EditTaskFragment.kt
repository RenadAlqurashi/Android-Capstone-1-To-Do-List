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
    private lateinit var taskEditButton: FloatingActionButton
    private lateinit var showDate: TextView


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
        taskEditButton = view.findViewById(R.id.taskEditButton_editTaskFragment)
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


        val args: EditTaskFragmentArgs by navArgs()
        val editTaskKey= args.editTaskKey
        taskTitle.setText(args.editTaskKey.taskName)
        taskDescription.setText(args.editTaskKey.taskDescription)
        showDate.setText(args.editTaskKey.taskDate)


        taskEditButton.setOnClickListener {

            viewModel = ViewModelProvider(this).get(EditTaskViewModel::class.java)

            val sdf = SimpleDateFormat("dd/M/yyyy  hh:mm")
            val currentDate = sdf.format(Date())


            args.editTaskKey.taskName = taskTitle.text.toString()
            args.editTaskKey.taskDescription = taskDescription.text.toString()
            args.editTaskKey.taskDate = showDate.text.toString()
            args.editTaskKey.creationDate = currentDate.toString()


            viewModel.update(args.editTaskKey)
            findNavController().navigate(R.id.action_editTaskFragment_to_mainFragment)
        }

    }

//    private fun updateDataToDataBase() {
//    }

}