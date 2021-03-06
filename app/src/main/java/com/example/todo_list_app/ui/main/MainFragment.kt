package com.example.todo_list_app.ui.main
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list_app.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var newTaskButton: FloatingActionButton
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        viewModel.getAllTasks().observe(viewLifecycleOwner,Observer{
            recyclerView.adapter= RecyclerViewAdapter(it,viewModel)
        })


        newTaskButton = view.findViewById(R.id.fab)
        newTaskButton.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_newTaskFragment)

        }


    }

}



