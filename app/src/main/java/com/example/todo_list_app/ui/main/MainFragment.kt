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
import androidx.appcompat.app.AppCompatActivity





class MainFragment : Fragment() {

    //    private var _binding: FragmentMainBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var rvAdapter: RecyclerViewAdapter
//    private lateinit var tasksList: ArrayList<Task>()
//
//    private lateinit var newTaskButtom: FloatingActionButton
//    private lateinit var viewModel: MainViewModel
//    private lateinit var recyclerView: RecyclerView
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

//    fun updateDatabase(viewModel: MainViewModel){
//
//    }
override fun onResume() {
    super.onResume()
    (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
}

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}


